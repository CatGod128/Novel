package com.Cat.Novel.Controller;

import com.Cat.Novel.Bean.Chapter;
import com.Cat.Novel.Bean.Novel;
import com.Cat.Novel.Service.ParseService;
import com.Cat.Novel.Service.QueryService;
import com.Cat.Novel.Utils.FreeMakerUtil;
import freemarker.template.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.io.IOException;
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
        FreeMakerUtil.printFile("ChapterInf.ftl", root,"ChapterInf");
      //  FreeMakerUtil.print("ChapterInf.ftl", root);
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
       // Novel novel=queryService.queryNovel(id);
        Novel novel =new Novel();
        novel.setNovelName("剑来");
        List<Chapter> chapterList=queryService.queryChapterList(id);
        Map root = new HashMap();
        root.put("NovelName",novel.getNovelName());
        root.put("ChapterList", chapterList);
        FreeMakerUtil.printFile("NovelInfo.ftl", root,"NovelInfo");
       // FreeMakerUtil.print("ChapterInf.ftl", root);
        model.addAttribute("NovelName",novel.getNovelName());
        model.addAttribute("ChapterList",chapterList);
        return "NovelInfo";
    }



    }




