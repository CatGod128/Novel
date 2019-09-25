package com.Cat.Novel.Utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.Map;

import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import freemarker.cache.ClassTemplateLoader;
import freemarker.cache.NullCacheStorage;
import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import freemarker.template.TemplateNotFoundException;

/**
 * FreeMaker 模板引擎
 * 
 * @author Cat
 *
 */
public class FreeMakerUtil {
	 private static final Configuration CONFIGURATION = new Configuration(Configuration.VERSION_2_3_22);

	static{
    	  //这里用来指定模板所在的路径，本项目配置在resources/templates目录下，springBoot项目会默认到resources下读文件
		  CONFIGURATION.setTemplateLoader(new ClassTemplateLoader(FreeMarkerTemplateUtils.class, "/templates"));
    	  CONFIGURATION.setDefaultEncoding("UTF-8");
    	  CONFIGURATION.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
    	  CONFIGURATION.setCacheStorage(NullCacheStorage.INSTANCE);
    	  }

	public static Template getTemplate(String templateName) throws TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException{
    	
    	  return CONFIGURATION.getTemplate(templateName);
    	  }
	/**
	 * 打印到控制台(测试用)
	 *  @param ftlName
	 */
	public static void print(String ftlName, Map<String,Object> root) throws Exception{
		try {
			Template temp = getTemplate(ftlName);		//通过Template可以将模板文件输出到相应的流
			temp.process(root, new PrintWriter(System.out));
		} catch (TemplateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 输出到输出文件
	 * @param ftlName   ftl文件名
	 * @param root		传入的map
	 * @param outFile	输出后的文件全部路径
	 * @param filePath	输出前的文件上部路径
	 */
	public static void printFile(String ftlName, Map<String,Object> root, String outFile, String filePath) throws Exception{
		try {
			File file = new File(filePath + outFile);
			if(!file.getParentFile().exists()){				//判断有没有父路径，就是判断文件整个路径是否存在
				file.getParentFile().mkdirs();				//不存在就全部创建
			}
			Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "utf-8"));
			Template template = getTemplate(ftlName);
			template.process(root, out);					//模版输出
			out.flush();
			out.close();
		} catch (TemplateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
