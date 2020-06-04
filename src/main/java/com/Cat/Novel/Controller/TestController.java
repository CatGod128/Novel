package com.Cat.Novel.Controller;

import com.Cat.Novel.Bean.Novel;
import com.Cat.Novel.Service.QueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * 临时测数据
 * @author Mr.Cat
 * @date 2019/11/5 13:10
 */
@Controller
public class TestController {
    @Autowired
    private QueryService queryService;

    @RequestMapping("queryALL")
    @ResponseBody
    public List<Novel> queryAll(){
       List<Novel> list=queryService.queryALL();
        System.out.println("123");
       return list;
    }

    //页面请求
    @RequestMapping("/socket/{cid}")
    public String socket(@PathVariable("cid") String cid) {
        System.out.println(cid);
        return "socket";
    }


}
