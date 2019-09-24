package com.Cat.Novel.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 测试
 * @author 13001
 *
 */
@Controller
public class TestController {

           @RequestMapping("/Test")
           @ResponseBody
           public static String TestMethod() {
        	   String result="收到请求";
        	   return result;
           }
}
