package com.Cat.Novel.Utils;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;



/**
 * @author Mr.Cat
 * @date 2020-2-21 16:45
 */
public class HtmlUnitUtil {
    public static void main(String[] args) {
        String url = "https://music.163.com/";
        //构造一个webClient 模拟Chrome 浏览器
        WebClient webClient = new WebClient(BrowserVersion.CHROME);

        //支持JavaScript
        webClient.getOptions().setJavaScriptEnabled(true);
        webClient.getOptions().setCssEnabled(false);
        webClient.getOptions().setActiveXNative(false);
        webClient.getOptions().setCssEnabled(false);
        webClient.getOptions().setThrowExceptionOnScriptError(false);
        webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
        webClient.getOptions().setTimeout(5000);
        HtmlPage rootPage = null;
        try {
            rootPage = webClient.getPage(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //设置一个运行JavaScript的时间
        webClient.waitForBackgroundJavaScript(5000);
        String html = rootPage.asXml();
        Document document = Jsoup.parse(html);
        System.out.println(document);
    }
}
