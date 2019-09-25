package com.Cat.Novel.Service;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import com.Cat.Novel.Bean.Chapter;
import com.Cat.Novel.Utils.HtttpClientUtil;
import com.Cat.Novel.Utils.RegexUtils;

@Service
public class ParseService {

	public  int ParsePage(String url) throws ClientProtocolException, IOException {
		Document doc = HtttpClientUtil.getDoc(url);
		Elements elements = doc.getElementsByTag("a");
		for (Element e : elements) {
			if (e.text().equals("剑来")) {
				System.out.println(e.attr("href"));
				openNewPage(e.attr("href"));
			}
		}
		return 0;
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
	 * 打开新的页面
	 * 
	 * @param url 网页地址路径            
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public  void openNewPage(String url) throws ClientProtocolException, IOException {

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
		for(Chapter a: list) {
			System.out.println(a);
		}
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
			chapter.setContent(doc.getElementById("content").toString());		  			
		}
		return chapter;
	}
}
