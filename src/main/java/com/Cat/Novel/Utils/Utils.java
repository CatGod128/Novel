package com.Cat.Novel.Utils;
 
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.Cat.Novel.Bean.Chapter;
import com.Cat.Novel.Bean.Novel;
 

 
public class Utils {
	//获取html的doc   @1			
	public static Document GetDocument(String url) throws IOException {
		Document doc=Jsoup.connect(url).get();
		return doc;
	}
	/*
	 * 获取每本小说的信息
	 */
 
	//获取小说名传入排行榜页面的url     @2		调用@1
	public static List<Novel> getNotename(Document doc) {
		List<Novel> notelist=new ArrayList<Novel>();
		Elements elements=doc.getElementsByTag("dt");
		Novel eachNovel;
		for(Element e:elements){
			eachNovel=new Novel();
			eachNovel.setUrl(e.select("a").attr("href"));
			eachNovel.setNovelName(e.text());	
			notelist.add(eachNovel);
			}
		return notelist;
	}
	 
	//单本小说每个章节信息.     这个直接用在了WriteToTxt中了。
	public static List<Chapter> getChapter(List<Novel> notelist) throws IOException {
		List<Chapter> chapList=new ArrayList<Chapter>();
		for(Novel note:notelist){
			
			//获取index页面根节点
			Document document=GetDocument(note.getUrl());
			
			//获取章节信息的父节点后获取章节节点。
			Elements element=document.select("dl.chapterlist").first().select("a[href$=.html]");
			
			//拼接chapterUrl字符串。
			String url1=note.getUrl().replace("index.html", "");
			String url2=url1;
			
			for(Element e:element){
				Chapter cInfo=new Chapter();
				url2=url1;
				url2+=e.attr("href");
				
				//给chapterUrl和chapterName赋值
				cInfo.setChapterName(e.text());
				cInfo.setUrl(url2);
				
				//获取chapterText
				Document document2=GetDocument(url2);
				
				//控制txt文本的格式。
				String list="  ";
				Element element3=document2.getElementById("BookText");
				String[] s=element3.text().split(" ");
				for(String l:s){
					list+=l+"\r\n  ";
					cInfo.setContent(list);
				}
				chapList.add(cInfo);
			}
			System.out.println("finish");
		}
		
		return chapList;
	}	
}