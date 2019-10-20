package com.Cat.Novel.Service;

import com.Cat.Novel.Bean.Chapter;
import com.Cat.Novel.Bean.Novel;
import com.Cat.Novel.Mapper.QueryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 查询数据库
 */
@Service
public class QueryService {

    @Autowired
    private QueryMapper queryMapper;

    /**
     * 查询章节信息
     * @param id
     * @return
     */
    public Chapter queryChapterUrl(String id) {
        return  queryMapper.queryChapterUrl(id);
    }

    public Novel queryNovel(String id) {
        return queryMapper.queryNovelInfo(id);
    }

    public List<Chapter> queryChapterList(String id) {
        return queryMapper.queryChapterList(id);
    }

    public int isExistChapter(String ChapterName) {
        return queryMapper.isExistChapter(ChapterName);
    }

    public String getPrivousID(Map map) {
        return queryMapper.getPrivousID(map);
    }
}
