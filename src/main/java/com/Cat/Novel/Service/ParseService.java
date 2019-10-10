package com.Cat.Novel.Service;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.apache.http.client.ClientProtocolException;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import com.Cat.Novel.Bean.Chapter;
import com.Cat.Novel.Bean.Novel;
import com.Cat.Novel.Utils.HtttpClientUtil;
import com.Cat.Novel.Utils.RegexUtils;

@Service
public class ParseService {
	
	

	/**
	 * 解析网页
	 * @param url
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public  List<String> ParsePage(String url) throws ClientProtocolException, IOException {
		Elements elements=null;
		Document doc = HtttpClientUtil.getDoc(url);
		List<String> list =new ArrayList<>();
		System.out.println(url);
		if(url.contains("xiaoshuo")) { //小说列表页
			 elements = doc.select("[class=s2] a");	
			 for (Element e : elements) {
					list.add(e.attr("href"));
					}
		}else {
			 elements = doc.select("dd a");	
			 for (Element e : elements) {
					list.add(e.attr("href"));
					}
		}
		

		return list;
	}
	/**
	 * 获取当前小说的章节总数
	 * 
	 * @param url
	 * @return
	 * @throws IOException
	 * @throws ClientProtocolException
	 */
	public  int getChatersNumber(String url) throws ClientProtocolException, IOException {

		Document doc = HtttpClientUtil.getDoc(url);
		Elements elements = doc.getElementsByTag("dd");
		int size = elements.size();
		return size;
	}

	/**
	 * 解析章节
	 * 
	 * @param url 网页地址路径            
	 * @return 
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public  List<Chapter> getChapters(String url) throws ClientProtocolException, IOException {

		Document doc = HtttpClientUtil.getDoc(url);
		Elements elements = doc.getElementsByTag("dd");
		List<Chapter> list=new ArrayList<>();
	//	System.out.println(doc.toString());
		// 获取章节总数，便于排序输出
		int ChapterNums = getChatersNumber(url);
		for (int i = 10; i < ChapterNums; i++) {
			
			String Chapterurl = elements.get(i).select("a").attr("href");
			Chapter chapter=new Chapter();
			if (null != Chapterurl && "" != Chapterurl)
				chapter.setChapterName(elements.get(i).text());
				chapter.setUrl(Chapterurl);
				chapter.setRealName(RegexUtils.getChapterName(chapter.getChapterName()).trim());
				list.add(chapter);
		}
		return list;
	}
	
	/**
	 * 获取章节内容
	 * @param url
	 * @return
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	public Chapter getChapterContent(String url) throws ClientProtocolException, IOException {
		String content="";
		Chapter chapter=new Chapter();
		Document doc = HtttpClientUtil.getDoc(url);
		chapter.setChapterName("第三章  日出");
		if (null != doc.getElementById("content")) {
			content=doc.getElementById("content").toString();
		//	content=new String(content.getBytes("iso-8859-1"),"utf-8");
			chapter.setContent(content);		 
		
		}
		return chapter;
	}
	/**
	 * 解析出小说信息
	 * @return
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	public Novel parseNovel(String url) throws ClientProtocolException, IOException{
		Novel novel=new Novel();
		Document doc = HtttpClientUtil.getDoc(url);
		Elements elements= doc.select("info h1");
		for(Element e: elements){
			novel.setNovelName(e.text());
			System.out.println(novel.getNovelName());
		}
		return novel;
	}
}

