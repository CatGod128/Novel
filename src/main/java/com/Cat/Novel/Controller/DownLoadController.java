package com.Cat.Novel.Controller;

import com.Cat.Novel.Service.DownLoadNovelService;
import com.Cat.Novel.Utils.ImgDownloadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 下载资源
 *
 * @author Mr.Cat
 * @date 2020-1-18 9:36
 */
@Controller
public class DownLoadController {

    @Autowired
    private DownLoadNovelService downLoadNovelService;

    @RequestMapping("/DownLoad")
    @ResponseBody
    public String DownLoad(HttpServletRequest request) throws IOException {
        String URL =request.getParameter("url");
        String message = "下载完成";
        // 下载图片
            ImgDownloadUtil.ZipImg(URL);
            message ="图片" + message;
        return message;
    }
}
