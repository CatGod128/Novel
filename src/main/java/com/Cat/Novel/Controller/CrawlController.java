package com.Cat.Novel.Controller;

import com.Cat.Novel.Bean.Chapter;
import com.Cat.Novel.Service.ParseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.List;

/**
 * 爬取小说
 * @author Mr.Cat
 * @date 2019/10/18 11:57
 */
@Controller
public class CrawlController {

    @Autowired
    private ParseService parseService;
    /**
     * 爬取小说
     * @param url
     * @return
     * @throws IOException
     */
    @RequestMapping("/Crawl")
    @ResponseBody
    public String Craw(String url) throws IOException {
        url="https://www.biquge5200.cc/75_75584/";
       String Msg= parseService.parseChapters(url);
        return  Msg;
    }
}
