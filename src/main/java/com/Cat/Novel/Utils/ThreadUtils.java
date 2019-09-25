package com.Cat.Novel.Utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.Cat.Novel.Bean.Chapter;
import com.Cat.Novel.Bean.Novel;

/**
 * 线程工具类
 * @author 13001
 *
 */
public class ThreadUtils implements Runnable  {

	private Novel novel;
	List<Chapter> chapList = new ArrayList<Chapter>();
 
	public ThreadUtils(Novel novel) throws IOException {
		
		this.novel = novel;
	
	}
 
	@Override
	public void run() {
		try {
			
			// 创建文件
			File file = new File("D:\\剑来\\" + novel.getNovelName() + ".txt");
			if (!file.exists()) {
				try {
					file.createNewFile();
				} catch (IOException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
			} else {
				file.delete();
			}
			FileWriter fW = new FileWriter(file, true);
			BufferedWriter bw = new BufferedWriter(fW);
			bw.write("   " + novel.getNovelName() + "\r\n");
			bw.write("   " + novel.getWirterid() + "\r\n");
			bw.write("***copy@by nice***\r\n");
			System.out.println(novel.getUrl());
			Document document = Utils.GetDocument("https://www.biquge5200.cc/75_75584/");
			System.out.println(document.toString()+"--");
			// 获取章节信息的父节点后获取章节节点。
			Elements element = document.getElementsByTag("dd");
		
			for (Element e : element) {
				Chapter cInfo = new Chapter();
                String url=e.select("a").attr("href");
				// 给chapterUrl和chapterName赋值
				cInfo.setChapterName(e.text());
				cInfo.setUrl(url);
                System.out.println("111");
				// 获取chapterText
				Document document2 = HtttpClientUtil.getDoc(url);
 
				// 控制txt文本的格式。
				String list = "  ";
				Element element3 = document2.getElementById("content");
				String[] s = element3.text().split(" ");
				for (String l : s) {
					list += l + "\r\n  ";
					cInfo.setContent(list);
				}
				chapList.add(cInfo);
			}
 
			
			for (Chapter c : chapList) {
				bw.write(c.getChapterName() + "\r\n");
				bw.write(c.getContent() + "\r\n");
			}
			bw.close();
			fW.close();
			
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
 
	}

}
