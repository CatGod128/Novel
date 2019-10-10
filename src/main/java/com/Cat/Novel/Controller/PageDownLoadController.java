package com.Cat.Novel.Controller;



import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.http.client.ClientProtocolException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.Cat.Novel.Service.ParseService;
import com.Cat.Novel.Service.QueueRepositoryService;
import com.mysql.jdbc.StringUtils;

/**
 * 爬取小说
 * @author 13001
 *
 */
@Configuration
@Controller
public class PageDownLoadController {
	
	@Autowired
	private  ParseService parseService;
	@Autowired
	private QueueRepositoryService queueRepositoryService;

	
	private ExecutorService newFixedThreadPool=Executors.newFixedThreadPool(5);

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
		 url = "https://www.biquge5200.cc/xuanhuanxiaoshuo/";
		 queueRepositoryService.addHighLevel(url);
		 startCraw();
		 return "完成";
		 
	 }
	 /**
	  * 开启一个爬虫
	  * @throws ClientProtocolException
	  * @throws IOException
	  */
	 public void startCraw() throws ClientProtocolException, IOException {
		 while(true) {
			 //从队列中获取地址路径
			

			newFixedThreadPool.execute(new Runnable() {
				String  url =queueRepositoryService.poll();
				@Override
				public void run() {
					
					System.out.println("当前线程为"+Thread.currentThread().getId()+"抓取"+url);
					
					if(StringUtils.isNullOrEmpty(url)) {
						System.out.println("队列中路径解析完成");		
						try {
							Thread.currentThread().sleep(5000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}else {
						List<String> list;
						try {
							list = parseService.ParsePage(url);
							for(String str :list) {
								if(str.contains("xiaoshuo")) {  //小说详情页
									queueRepositoryService.addHighLevel(str);
								}else {					
									queueRepositoryService.addLowLevel(str);
								}
							}
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						
					}
					try {
						Thread.currentThread().sleep(3000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
	/*		if(StringUtils.isNullOrEmpty(url)) {
				System.out.println("队列中路径解析完成");		
			}else {
				List<String> list;
					list = parseService.ParsePage(url);
					for(String str :list) {
						if(str.contains("xiaoshuo")) {  //小说详情页
							queueRepositoryService.addHighLevel(str);
						}else {					
							queueRepositoryService.addLowLevel(str);
						}

	 }
	}*/
 }}}
