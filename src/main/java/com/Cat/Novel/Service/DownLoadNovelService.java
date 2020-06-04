package com.Cat.Novel.Service;

import org.springframework.stereotype.Service;

/**
 * 下载小说到本地
 * @author Mr.Cat
 * @date 2020-1-18 9:54
 */
@Service
public class DownLoadNovelService {

    public String downLoadNovel(String Name){
        String message="小说下载成功";
        return message;
    }
}
