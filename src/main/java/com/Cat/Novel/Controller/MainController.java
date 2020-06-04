package com.Cat.Novel.Controller;

import com.Cat.Novel.Bean.Chapter;
import com.Cat.Novel.Bean.Novel;
import com.Cat.Novel.Service.ParseService;
import com.Cat.Novel.Service.QueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

/**
 * 前台访问
 * Mr.Cat
 */

@Controller

public class MainController {


    @Autowired
	private  ParseService parseService;
    @Autowired
    private QueryService queryService;
    //用于 进度条返回计数
    private static int count=0;

    /**
     * 访问章节内容
     * @param model
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping("/queryChapterInfo")
    public String queryChapterInfo(Model model,String id) throws Exception{
        Chapter  chapter=queryService.queryChapterUrl(id);
        chapter.setContent(parseService.getChapterContent(chapter.getUrl()));
        Map root = new HashMap();
        root.put("ChapterName",chapter.getChapterName());
        root.put("Content", chapter.getContent());
        root.put("NovelId", chapter.getNovelId());
        root.put("privousId", chapter.getPrivousId());
        root.put("nextId", chapter.getNextId());
        model.addAttribute("ChapterName",chapter.getChapterName());
        model.addAttribute("Content",chapter.getContent());
        model.addAttribute("NovelId",chapter.getNovelId());
        model.addAttribute("privousId", chapter.getPrivousId());
        model.addAttribute("nextId",chapter.getNextId());
        return "ChapterInf";
    }

    /**
     * 访问小说章节
     * @param model
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping("/queryNovelInfo")
    public String queryNovelInfo(Model model,String id) throws Exception{
        Novel novel=queryService.queryNovel(id);
        List<Chapter> chapterList=queryService.queryChapterList(id);
        model.addAttribute("NovelName",novel.getNovelName());
        model.addAttribute("ChapterList",chapterList);
        return "NovelInfo";
    }

    /**
     * 访问主页
     * @return
     */
   @RequestMapping("/admin")
    public String index(){
       return  "index";
   }

    /**
     * 用于不用渲染数据的静态页面跳转
     * @param pageName
     * @return
     */
   @RequestMapping("/queryPage/{pageName}")
    public String  pageFlow(@PathVariable String pageName){
       if(pageName.contains("crawl")){
           return "crawls/"+pageName;
       }
       return pageName;
   }

    /**+
     * 测试进度条设置
     * @param Url
     * @return
     */
   @RequestMapping("/testProcess")
   @ResponseBody
    public int testProcess(String Url){
       if(count <= 100){
           addCount();
           return  count;
       }else {
           return 1000;
       }
   }

    public  void addCount(){
       count++;
    }

    }




