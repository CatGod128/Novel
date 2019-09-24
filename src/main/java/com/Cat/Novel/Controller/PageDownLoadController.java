package com.Cat.Novel.Controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import org.apache.http.client.ClientProtocolException;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.Cat.Novel.Utils.HtttpClientUtil;

/**
 * 抓取页面
 * @author 13001
 *
 */
@Controller
public class PageDownLoadController {

	/**
	 * 抓取页面
	 * @param url
	 * @return
	 * @throws IOException 
	 * @throws ClientProtocolException 

	 */
	 @RequestMapping("/Craw")
	 @ResponseBody
	 public static String PageDownload(String url) throws ClientProtocolException, IOException  {
		 url = "https://www.biquge5200.cc/";
		 Document doc = null;
		try {
			 doc = HtttpClientUtil.getDoc(url);
			 
			
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Elements elements = doc.getElementsByTag("a");
		for (Element e : elements) {
			if (e.text().equals("剑来")) {
				System.out.println(e.attr("href"));
				openNewPage(e.attr("href"));
			}
		}
		return "完成";
	 }
	 
	 @RequestMapping("/good")
	 @ResponseBody
	 public static  String aa() {
		 System.out.println("12");
		 return "收到";
	 }
	 /**
		 * 获取当前小说的章节总数
		 * 
		 * @param url
		 * @return
		 * @throws IOException
		 * @throws ClientProtocolException
		 */
		public static int getChatersNumber(String url) throws ClientProtocolException, IOException {

			Document doc = HtttpClientUtil.getDoc(url);
			Elements elements = doc.getElementsByTag("dd");
			int size = elements.size();
			return size;
		}

		/**
		 * 打开新的页面
		 * 
		 * @param url 网页地址路径            
		 * @throws ClientProtocolException
		 * @throws IOException
		 */
		public static void openNewPage(String url) throws ClientProtocolException, IOException {

			Document doc = HtttpClientUtil.getDoc(url);
			Elements elements = doc.getElementsByTag("dd");
			// 获取章节总数，便于排序输出
			int ChapterNums = getChatersNumber(url);
			for (int i = 10; i < 20; i++) {
				String Chapterurl = elements.get(i).select("a").attr("href");
				if (null != Chapterurl && "" != Chapterurl)
					System.out.println(Chapterurl + "章节名：" + elements.get(i).text());
				WriteText(Chapterurl);
			}
		}

		/**
		 * 写入txt
		 * 
		 * @throws IOException
		 */
		public static void WriteText(String url) throws IOException {
			Document doc = HtttpClientUtil.getDoc(url);
			if (null != doc.getElementById("content")) {
				String content = doc.getElementById("content").text();
				String name = doc.getElementsByTag("h1").text();
				/* 写入Txt文件 */
				File writename = new File("D:\\剑来\\剑来.txt"); // 相对路径，如果没有则要建立一个新的output。txt文件
				OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(writename, true));
				out.write("\r\n" + name + "\r\n" + content); // \r\n即为换行
				out.flush(); // 把缓存区内容压入文件
				out.close(); // 最后记得关闭文件o
			}
		}

	}
