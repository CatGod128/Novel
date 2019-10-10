package com.Cat.Novel.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * 测试
 * @author 13001
 *
 */
@Controller
public class TestController {

           @RequestMapping("/getData")
           @ResponseBody
           public static String TestMethod() {
        	   String result="收到请求";
        	   return result;
           }
           @RequestMapping("/getechart")
           public ModelAndView echart() {
        	   ModelAndView mav =  new ModelAndView("echart");
        	   String result="收到请求";
        	   System.out.println(result);
        	   return mav;
           }
           
}
