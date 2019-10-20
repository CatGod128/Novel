package com.Cat.Novel.Service;

import com.Cat.Novel.Bean.Chapter;
import com.Cat.Novel.Mapper.StoreMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 存储数据库
 */
@Service
public class StoreService {

    @Autowired
    private StoreMapper storeMapper;

    public int saveChapter(Chapter chapter) {
        return storeMapper.saveChapter(chapter);
    }
    public int saveNextId(Map map){
        System.out.println(map.toString());
        return  storeMapper.saveNextID(map);
    };
}
