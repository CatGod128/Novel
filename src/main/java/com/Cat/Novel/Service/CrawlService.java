package com.Cat.Novel.Service;

import com.Cat.Novel.Bean.CrawlerInfo;
import com.mysql.jdbc.StringUtils;
import org.apache.http.client.ClientProtocolException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author Mr.Cat
 * @date 2019/10/22 15:45
 */
@Service
public class CrawlService {

    @Autowired
    private  ParseService parseService;
    @Autowired
    private  QueueRepositoryService queueRepositoryService;

    private Logger logger = LoggerFactory.getLogger(getClass());

    public void StartCrawl(String url) throws IOException {
        System.out.println("开始爬取。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。");
        queueRepositoryService.addHighLevel(url);
        Crawl();
    }
    /**
     * 开启一个爬虫
     * @throws ClientProtocolException
     * @throws IOException
     */
    public  void Crawl() throws ClientProtocolException, IOException {

        while (queueRepositoryService!=null) {
            List<Future<CrawlerInfo>> futurelist = new ArrayList<Future<CrawlerInfo>>();

            //从队列中获取地址路径
            String url = queueRepositoryService.poll();
            if (StringUtils.isNullOrEmpty(url)) {
                System.out.println("队列中路径解析完成");
                break;
            } else {
                List<String> list;
                list = parseService.ParsePage(url);
                for (String str : list) {
                    //小说详情
					if (str.contains("xiaoshuo")) {
						 queueRepositoryService.addHighLevel(str);
					 } else if(str.contains("html")){
                                break;
                    }else {
                        CrawlerInfo crawlerInfo=new CrawlerInfo();
                        crawlerInfo.setBeginDate(new Date());
                        crawlerInfo.setNovelName(str);
                        Future<CrawlerInfo> future = parseService.parseChapters(str,crawlerInfo);
                        futurelist.add(future);
                        queueRepositoryService.addLowLevel(str);
					 }
                }
                for(Future<CrawlerInfo> future:futurelist) {
                    while(true) {
                        if(future.isDone()) {
                            try {
                                logger.info(future.get().getNovelName()+"耗时"+future.get().getDuration()+"毫秒");
                            } catch (InterruptedException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            } catch (ExecutionException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                            break;
                        }
                    }
                }

            }
        }
        logger.info("下载完成----------------------------------------------------");
    }
}
