package com.Cat.Novel.Mapper;


import com.Cat.Novel.Bean.Chapter;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Mapper
public interface StoreMapper {

    int saveChapter(Chapter chapter);

    int saveNextID(Map map);
}
