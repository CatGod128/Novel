package com.Cat.Novel.Utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 * HttpClient工具类
 * @author MR.Cat
 * @version 
 */
public class HtttpClientUtil {

	/**
	 * 将html处理doc
	 * @param url
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public static Document getDoc(String url)   {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(url);
		// 模拟请求头User-Agent
		httpGet.setHeader("User-Agent","Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:50.0) Gecko/20100101 Firefox/50.0");
		CloseableHttpResponse response = null;
		try {
			response = httpClient.execute(httpGet);
		} catch (IOException e) {
			e.printStackTrace();
		}
		HttpEntity entity = response.getEntity();
		Document doc = null;
		try {
			doc = Jsoup.parse(EntityUtils.toString(entity,"UTF-8"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			response.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			httpClient.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return doc;
	}

	public static Document getDocByConnect(String url) throws IOException {
		Connection connect = Jsoup.connect(url);
		connect.header(
				"Accept",
				"text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
		connect.header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.25 Safari/537.36 Core/1.70.3741.400 QQBrowser/10.5.3863.400");
		connect.header("Connection", "Keep-Alive");
		connect.header("Accept-Encoding", "gzip,deflate,sch");
		connect.header("Content-Type","application/xml");
		Document doc = connect.timeout(30000).get();
		return  doc;
	}
	public static Document get(String url) throws IOException {
		Connection connection = Jsoup.connect(url);
		Map<String, String> header = new HashMap<String, String>();
		header.put(
				"Accept",
				"text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
		header.put("Accept-Encoding", "gzip, deflate");
		header.put("Accept-Language", "zh-CN,zh;q=0.9");
		header.put("Cache-Control", "max-age=0");
		header.put("Connection", "close");
		header.put(
				"User-Agent",
				"Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.25 Safari/537.36 Core/1.70.3741.400 QQBrowser/10.5.3863.400");
		Document doc = connection.ignoreContentType(true).headers(header).timeout(300000).get();
		return  doc;
	}
}
