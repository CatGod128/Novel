package com.Cat.Novel.Utils;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 下载小说到服务器
 * @author Mr.Cat
 * @date 2020-1-18 11:01
 */
public class NovelDownloadUtil {
    private Logger logger = LoggerFactory.getLogger(getClass());
    /**
     * 解析出章节地址和章节名称
     * @param url   小说的地址路径
     * @return
     * @throws IOException
     */
    public  static List<Map<String,String>> parseNovel(String url) throws IOException {
        List<Map<String,String>> list=new ArrayList<>();
        Document doc = HtttpClientUtil.get(url);
        Elements elements = doc.select("dd a");
        for(Element e:elements){
            Map<String,String> map=new HashMap<>();
            map.put("chapterName",e.text());
            map.put("chapterUrl",e.absUrl("href"));
            list.add(map);
        }
        return  list;
    }

    private static String parseChapter(Map<String,String> map) throws IOException {
        File txt =new File("D://剑来//a.txt");
        String content=getChapterContent(map.get("chapterUrl"));
        try {
            OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(txt, true));
            out.write(map.get("chapterName") +"\r\n");
            out.write(content);
            out.flush(); // 把缓存区内容压入文件
            out.close(); // 最后记得关闭文件o
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 获取章节内容
     * @param url
     * @return
     * @throws IOException
     */
    public static  String  getChapterContent(String url) throws IOException {
        String content="";
        Document doc = HtttpClientUtil.get(url);
        if (null != doc.getElementById("content")) {
            content=doc.getElementById("content").toString();
        }
        return content;
    }

    public static void main(String[] args) throws IOException {
        String url="https://www.biquge5200.cc/75_75584/";
        List<Map<String,String>> list=parseNovel(url);
        list.forEach(map -> {
            try {
                parseChapter(map);
                System.out.println(map.get("chapterName") +"--解析完成");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        System.out.println("小说下载完成");
        ;
    }

}
