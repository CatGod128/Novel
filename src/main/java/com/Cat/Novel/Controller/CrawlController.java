package com.Cat.Novel.Controller;

import com.Cat.Novel.Bean.CrawlerInfo;
import com.Cat.Novel.Service.CrawlService;
import com.Cat.Novel.Service.ParseService;
import org.apache.http.client.ClientProtocolException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Future;

/**
 * 爬取小说
 * @author Mr.Cat
 * @date 2019/10/18 11:57
 */
@Controller
public class CrawlController {


    @Autowired
    private CrawlService crawlService;
    @Autowired
    private ParseService parseService;
    @Resource(name = "taskExecutor")
    private ThreadPoolTaskExecutor taskExecutor;

    /**
     * 抓取页面
     * @param url
     * @return
     * @throws IOException
     * @throws ClientProtocolException
     */
    @RequestMapping("/Spider")
    @ResponseBody
    public  String StartCrawl(String url) throws ClientProtocolException, IOException  {
        //url = "https://www.biquge5200.cc/xuanhuanxiaoshuo/";
        CrawlerInfo crawlerInfo=new CrawlerInfo();
        crawlerInfo.setBeginDate(new Date());
        crawlerInfo.setNovelName(url);
        Future<CrawlerInfo> future = parseService.parseChapters(url,crawlerInfo);
        return "完成";
    }
    /**
     * 抓取页面
     * @param url
     * @return
     * @throws IOException
     * @throws ClientProtocolException
     */
    @RequestMapping("/Crawl")
    @ResponseBody
    public  String StartSpider(String url) throws ClientProtocolException, IOException  {
      // url = "https://www.biquge5200.cc/xuanhuanxiaoshuo/";
        crawlService.StartCrawl(url);
        return "完成";
    }
    @Test
    public void test1() {
        //同步辅助类需要通过这个类来控制所有的线程都执行完成;
        List<String> list = new ArrayList<>();

        CountDownLatch countDownLatch = new CountDownLatch(1000);
        for (int i = 0; i < 1000; i++) {
            final int j = i;
            taskExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println(Thread.currentThread().getName() + "---" + j);

                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        countDownLatch.countDown();  //这个不管是否异常都需要数量减,否则会被堵塞无法结束
                    }
                }
            });
        }
        try {
            countDownLatch.await(); //保证之前的所有的线程都执行完成，才会走下面的；
            // 这样就可以在下面拿到所有线程执行完的集合结果
        } catch (Exception e) {
            System.out.println("出现异常");
        }
    }


}
