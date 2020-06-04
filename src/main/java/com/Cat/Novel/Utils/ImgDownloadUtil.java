package com.Cat.Novel.Utils;


import com.Cat.Novel.WebSocket.WebSocketServer;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.context.annotation.PropertySource;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.*;



/**
 * @author Mr.Cat
 * @date 2020-1-15 19:20
 * 下载图片到服务的工具类
 */
@PropertySource("classpath:common.properties")
public class ImgDownloadUtil {

   /* @Value("${ImgPath}")
    private static  String path;
    @Value("${name}")
    private static String name;*/

   private  static  String finalPath;


    /**
     * 根据src的路径下载图片
     *
     * @param srcUrl
     * @return
     */
    public static String downLoadImg(String srcUrl, String imgName,String path)  {
        URL url = null;
        String Imgpath = path + imgName ;
        File file = new File(path);
        if(!file.exists()){
            file.mkdirs();
        }
        try {
            url = new URL(srcUrl);
            URLConnection connection = url.openConnection();
            connection.setRequestProperty("Referer",srcUrl);
            connection.setRequestProperty("User-Agent","Mozilla/5.0 (iPad; CPU OS 11_0 like Mac OS X) AppleWebKit/604.1.34 (KHTML, like Gecko) Version/11.0 Mobile/15A5341f Safari/604.1");
            connection.setDoInput(true);
            connection.setConnectTimeout(10 * 1000);
            DataInputStream dataInputStream = new DataInputStream(url.openStream());
            FileOutputStream fileOutputStream = new FileOutputStream(new File(Imgpath));
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024 * 1024 * 10];
            int length = 0;
            while ((length = dataInputStream.read(buffer)) != -1) {
                output.write(buffer, 0, length);
            }
            byte[] context = output.toByteArray();
            fileOutputStream.write(output.toByteArray());
            fileOutputStream.flush();
            dataInputStream.close();
            fileOutputStream.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println(imgName + "下载失败");
            return "";
        }
        System.out.println(imgName + "下载完成");
        return Imgpath;
    }

    /**
     * 根据网页地址解析所有的src的路径
     *
     * @param URL 网页地址
     * @return list  Img的src 路径
     */
    public static List<Map<String, String>> analysisSrcUrl(String URL)  {
        List<Map<String, String>> UrlList = new ArrayList<>();

        Document doc = null;
        try {
            //doc = HtttpClientUtil.getDocByConnect(URL);
            doc = HtttpClientUtil.get(URL);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Elements elements = doc.getElementsByTag("img");
        for (Element elemente : elements) {
            String imgUrl = elemente.absUrl("src");
            if(imgUrl == null && imgUrl == ""){
                imgUrl= elemente.absUrl("src2");
            }
            if (imgUrl != null && imgUrl != "") {
                Map<String, String> map = new HashMap<>();
                if (imgUrl.contains("?")){
                    imgUrl = imgUrl.substring(0,imgUrl.indexOf("?"));
                }
                //截取图片文件名
                String fileName = imgUrl.substring(imgUrl.lastIndexOf('/') + 1, imgUrl.length());
                try {
                    // 文件名里面可能有中文或者空格，所以这里要进行处理。但空格又会被URLEncoder转义为加号
                    String urlTail = URLEncoder.encode(fileName, "UTF-8");
                    // 因此要将加号转化为UTF-8格式的%20
                    imgUrl = imgUrl.substring(0, imgUrl.lastIndexOf('/') + 1) + urlTail.replaceAll("\\+", "\\%20");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                map.put("name", fileName);
                map.put("url", imgUrl);
                UrlList.add(map);
            }

        }
        return UrlList;
    }

    /**
     * 批量下载图片
     * @param url
     * @param path
     * @return
     * @throws IOException
     */
    public  static boolean  BatchDownload(String url,String path)  {
        boolean flag=false;
        String random=(int)((Math.random()*9+1)*100000)+"";
        finalPath=path + random+"//";
        List<Map<String, String>> UrlList = analysisSrcUrl(url);
        if(UrlList.size()==0){
            return flag;
        }
        UrlList.forEach(map -> {
                    downLoadImg(map.get("url"), map.get("name"), finalPath);
                }
        );
        flag=true;
        System.out.println("图片全部下载完成");
       return flag;
    }

    /**
     * 批量下载并压缩图片文件夹
     * @param url
     * @return
     * @throws IOException
     */
    public static String ZipImg(String url ) throws IOException {
        String message="压缩失败";
        String path="D://DownLoadIMg//";
        boolean flag=false;
        boolean Flag=BatchDownload(url,path);
        if(Flag){
         flag=ZipUtils.compressFileToZip(finalPath.substring(0,finalPath.lastIndexOf("//")));
        }else {
            message="下载失败";
        }
        if (flag) {
            message="压缩成功";
        }
        return message;
    }
    public static void main(String[] args) throws IOException {
        String url = "http://www.kugou.com/";
        System.out.println(ZipImg(url));
    }

}
