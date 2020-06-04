package com.Cat.Novel.Bean;

import java.util.Date;

/**
 * @author Mr.Cat
 * @date 2019/10/22 22:21
 */
public class CrawlerInfo {
    /**
     * 小说名字
     */
   private  String novelName;
    /**
     * 开始时间
     */
   private Date beginDate;
    /**
     * 结束时间
     */
   private Date endDate;

    public String getNovelName() {
        return novelName;
    }

    public void setNovelName(String novelName) {
        this.novelName = novelName;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * 爬取所耗时间
     * @return
     */
    public long getDuration() {
        return (endDate.getTime()-beginDate.getTime());
    }
}
