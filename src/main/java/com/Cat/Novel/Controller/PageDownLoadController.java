package com.Cat.Novel.Controller;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.Cat.Novel.Service.ParseService;

/**
 * 抓取页面
 * @author 13001
 *
 */
@Controller
public class PageDownLoadController {
	
	@Autowired
	private  ParseService parseService;

	/**
	 * 抓取页面
	 * @param url
	 * @return
	 * @throws IOException 
	 * @throws ClientProtocolException 

	 */
	 @RequestMapping("/Craw")
	 @ResponseBody
	 public  String PageDownload(String url) throws ClientProtocolException, IOException  {
		 url = "https://www.biquge5200.cc/";
		 int flag =parseService.ParsePage(url);
		 return "完成";
		 
	 }
	 /**
	  * 返回章节内容
	  * @param url
	  * @return
	 * @throws IOException 
	 * @throws ClientProtocolException 
	  */
	 @RequestMapping("/getContent")
	 @ResponseBody
	 public String getContent(String url) throws ClientProtocolException, IOException {
		 url="https://www.biquge5200.cc/75_75584/168658095.html";
		 return parseService.getChapterContent(url);
	 }
	}
