package com.Cat.Novel.Service;


import java.io.IOException;
import java.util.*;
import java.util.concurrent.Future;

import com.Cat.Novel.Bean.CrawlerInfo;
import org.apache.http.client.ClientProtocolException;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import com.Cat.Novel.Bean.Chapter;
import com.Cat.Novel.Bean.Novel;
import com.Cat.Novel.Utils.HtttpClientUtil;
import com.Cat.Novel.Utils.RegexUtils;

/**
 * @author 13001
 */
@Service
public class ParseService {

	@Autowired
	private StoreService storeService;
	@Autowired
	private QueryService queryService;

	private Logger logger = LoggerFactory.getLogger(getClass());



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

		//小说列表页
		if(url.contains("xiaoshuo")) {
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
	@Async
	public Future<CrawlerInfo> parseChapters(String url,CrawlerInfo crawlerInfo) throws ClientProtocolException, IOException {
		Novel novel=parseNovel(url);
		Document doc = HtttpClientUtil.getDoc(url);
		Elements elements = doc.getElementsByTag("dd");
		int ChapterNums = getChatersNumber(url);
		for (int i = 0; i < ChapterNums; i++) {
			String  novelId=novel.getId();
			//本章节路径
			String Chapterurl = elements.get(i).select("a").attr("href");
			if(Chapterurl.startsWith("/")){
                Chapterurl=url.substring(0,url.indexOf("/",13))+Chapterurl;
			}
			Map<String,Object> map=new HashMap<>();
			map.put("orderNum",i-1);
			map.put("novelId",novelId);
			//获取上一章ID
			String privousId = queryService.getPrivousID(map);
			Chapter chapter = new Chapter();
			if (null != Chapterurl && "" != Chapterurl) {
				chapter.setNovelId(novelId);
				chapter.setChapterName(elements.get(i).text());
				chapter.setUrl(Chapterurl);
				chapter.setPrivousId(privousId);
				chapter.setOrderNum(i);
				chapter.setRealName(RegexUtils.getChapterName(chapter.getChapterName()).trim());
			 	int isExist = queryService.isExistChapter(chapter.getChapterName());
			 	if(isExist==0){
					//存储数据
					int flag=storeService.saveChapter(chapter);
					if(i>0){
						Map<String,Object> nextmap=new HashMap<>();
						nextmap.put("novelId",novelId);
						nextmap.put("orderNum",i-1);
						nextmap.put("nextId",chapter.getId());
						int falg=storeService.saveNextId(nextmap);
					}
				}
				else if(isExist>1){
					System.out.println(chapter);
				}else{
					continue;
				}
			}
		}
		crawlerInfo.setEndDate(new Date());
		return new AsyncResult<CrawlerInfo> (crawlerInfo);
	}
	
	/**
	 * 获取章节内容
	 * @param url
	 * @return
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	public String  getChapterContent(String url) throws ClientProtocolException, IOException {
		String content="";
		Document doc = HtttpClientUtil.getDoc(url);
		if (null != doc.getElementById("content")) {
			content=doc.getElementById("content").toString();
		}
		return content;
	}
	/**
	 * 解析出小说信息
	 * @return
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	public Novel parseNovel(String url) throws ClientProtocolException, IOException{
		Novel novel=new Novel();
		novel.setUrl(url);
		Document doc = HtttpClientUtil.getDocByConnect(url);
		Elements elements= doc.getElementsByTag("h1");
		for(Element e: elements){
			novel.setNovelName(e.text());
		}
		//存储数据库
		//判断是否存在
		int isExist =queryService.isExistNovel(novel.getNovelName());
		if(isExist==0){
			boolean flag=storeService.saveNovel(novel);
		}
		logger.info(novel.toString());
		return novel;
	}
}

