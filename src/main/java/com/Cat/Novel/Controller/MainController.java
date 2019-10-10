package com.Cat.Novel.Controller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.Resource;

import org.apache.http.client.ClientProtocolException;
import org.jsoup.nodes.Document;
//import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.Cat.Novel.Bean.Chapter;
import com.Cat.Novel.Bean.Novel;
import com.Cat.Novel.Service.ParseService;
import com.Cat.Novel.Utils.FreeMakerUtil;
import com.Cat.Novel.Utils.HtttpClientUtil;
import com.Cat.Novel.Utils.ThreadUtils;
import com.Cat.Novel.Utils.Utils;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

@Controller

public class MainController {

    @Resource
    Configuration cfg;
    @Autowired
	private  ParseService parseService;

    @RequestMapping("/query")
    public String main(Model model,String id) throws Exception{
    	 String  url="";
    	if(id.equals("1")){
    		  url="https://www.biquge5200.cc/75_75584/168658095.html";
    	}else {
    		  url="https://www.biquge5200.cc/75_75584/168615131.html";
    	}
       
        Chapter chapter=parseService.getChapterContent(url);
        Map root = new HashMap();
        root.put("ChapterName",chapter.getChapterName());
        root.put("Content", chapter.getContent());
       // FreeMakerUtil.printFile("New.ftl", root,"ChapterInf");     
     //   FreeMakerUtil.print("New.ftl", root);     
        freeMarkerContent(root);
        model.addAttribute("ChapterName",chapter.getChapterName()); 
        model.addAttribute("Content",chapter.getContent());
        return "test";
    	
    }

    private void freeMarkerContent(Map<String,Object> root){
        try {
            Template temp = cfg.getTemplate("New.ftl");
            //以classpath下面的static目录作为静态页面的存储目录，同时命名生成的静态html文件名称
            String path=this.getClass().getResource("/").toURI().getPath()+"static/test.html";
            Writer file = new FileWriter(new File(path.substring(path.indexOf("/"))));
            temp.process(root, file);
            file.flush();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
/*    @Test
    public  void Test() throws ClientProtocolException, IOException {
    	 List<Novel> noteList = new ArrayList<Novel>();
    		Document doc = HtttpClientUtil.getDoc("https://www.biquge5200.cc/");
    		noteList = Utils.getNotename(doc);
    		System.out.println(noteList.size());
    		ExecutorService pool = Executors.newFixedThreadPool(10);
    		for (int i = 0; i < 1; i++) {
    			pool.execute(new Thread(new ThreadUtils(noteList.get(i))));
    		}
    		pool.shutdown();
    	}*/
    }




