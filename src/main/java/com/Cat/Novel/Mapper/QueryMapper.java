package com.Cat.Novel.Mapper;


import com.Cat.Novel.Bean.Chapter;
import com.Cat.Novel.Bean.Novel;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@Mapper
public interface QueryMapper {

    Chapter queryChapterUrl(String id);

    Novel queryNovelInfo(String id);

    List<Chapter> queryChapterList(String id);

    int isExistChapter(String ChapterName);

    String getPrivousID(Map map);
}
