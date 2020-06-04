package com.Cat.Novel.Service;

import com.Cat.Novel.Bean.Chapter;
import com.Cat.Novel.Bean.Novel;
import com.Cat.Novel.Mapper.StoreMapper;
import com.sun.scenario.effect.impl.sw.java.JSWBlend_SRC_OUTPeer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 存储数据库
 */
@Service
public class StoreService {

    @Autowired
    private StoreMapper storeMapper;

    /**
     * 存储章节信息
     * @param chapter
     * @return
     */
    public int saveChapter(Chapter chapter) {
        return storeMapper.saveChapter(chapter);
    }

    /**
     * 存储下一章节Id
     * @param map
     * @return
     */
    public int saveNextId(Map map){
        return  storeMapper.saveNextID(map);
    };

    /**
     * 存储小说信息
     * @param novel
     * @return
     */
    public boolean saveNovel(Novel novel) {
        return storeMapper.saveNovel(novel);
    }
}
