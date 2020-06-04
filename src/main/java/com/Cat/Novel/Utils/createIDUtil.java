package com.Cat.Novel.Utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

/**
 * 生成ID
 * @author Mr.Cat
 * @date 2019/10/21 23:02
 */
public class createIDUtil {

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyy");
    public static String createID(){
        String id = UUID.randomUUID().toString();
       // int Id = Integer.parseInt(id);
      //  String iid=""+Id;
        return id;
     /*   Random random = new Random();
        Date date = new Date();
        String resu = sdf.format(date)+(random.nextInt(1000)+1000);
        return resu;*/
    }
}
