package com.Cat.Novel.Utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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
	 private static String path="";


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
	 * 生成网页
	 * @param ftlName
	 * @param root
	 * @param htmlname
	 * @throws Exception
	 */
	public static void printFile(String ftlName, Map<String,Object> root, String htmlname) throws Exception{
		   Template template = getTemplate(ftlName);
		   String path=FreeMakerUtil.class.getClass().getResource("/").toURI().getPath()+"static/"+htmlname+".html";
		   Writer file = new FileWriter(new File(path.substring(path.indexOf("/"))));
		   template.process(root, file);    //模板输出
           file.flush();
           file.close();
	}
}
