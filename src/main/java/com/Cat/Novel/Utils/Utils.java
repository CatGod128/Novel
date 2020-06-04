package com.Cat.Novel.Utils;


import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * describe:
 *
 * @author wfd
 * @date 2019/08/27
 */
public class Utils {

	public static void main(String[] args) {
		int[] type = {1001, 1002, 1003, 2001, 2002, 2003, 6001, 6002, 6003, 7001, 7002, 7003, 4001, 4002, 4003};
		int[] enName = {-1, 0, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79,
				80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90};

		for (int i = 0; i < type.length; i++) {

			for (int j = 0; j < enName.length; j++) {
				String url = "https://music.163.com/discover/artist/cat?id=" + type[i] + "&initial=" + enName[j] + "";

				getData(url);
			}
		}
	}

	public static void getData(String url) {
		System.out.println(url);

		Document doc = null;
		try {
			doc = Jsoup.connect(url).userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.86 Safari/537.36")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3")
					.header("Accept-Encoding", "gzip, deflate, br")
					.header("Accept-Language", "zh-CN,zh;q=0.9,en;q=0.8")
					.header("Cookie", "ntes_nnid=6c5b87bd25a17a9fd9692580e5c94f78,1565912650142; _ntes_nuid=6c5b87bd25a17a9fd9692580e5c94f78; _iuqxldmzr_=32; WM_TID=LDdg6Rcj9ENEBRFUUFc4pPF4%2B6vTAn2G; WM_NI=R9FV8%2B3KZYFzFTyT7isTQivbb2VLf%2FzcQWAi%2BQdwZbxir0FYRR17q5zGEaYaTxwuyNrXXwr8kuNyRC2wcdeCeCAMWeyd1e8YJR%2FyJPg1kc3dMwiiFWuGVyQtxssnI3kBT04%3D; WM_NIKE=9ca17ae2e6ffcda170e2e6eed1cf4eacb8bb8ac641899e8fb3c85b928f8e84f333a7939790c14f8a90fdd3d92af0fea7c3b92a92e78ab7bb21f19596bac572868cb7b5b663b18c9f8af56aa2ee9aade525babd9fb8c463e98fac98d96abcb7bed1c553928effd8f43fba998b82dc6b98a8b996fc46b2889898f134a9ab829ad149f2a9ad85e849a79d8faed66fbbbcff86bb538a8ee19ac95ca5efa584b2708fa9a78ac55db7999a9ad480bcadbd8fcc39a99e9cd1b737e2a3; JSESSIONID-WYYY=yBXBK%2FIFCVHGtcBTi3%5CSUeDQMvfzApFAMBZzlZ%2BENNt7n2f9j2SCTvBRQpFACIc5EnGK3%2BtFhTQJWOhtCkJvHZ8olJ83RYG8Exukhj6Ftzw%2FBwylje03bjPW4Vl9IXXOHeNIRWxO4%2BKndGOJ0HjhnNZJtoESJht8PfF%2FfzAVXh6kOWiq%3A1566909226292")
					.header("Referer", "https://music.163.com/discover/artist/cat?id=1001&initial=65")
					.header("Upgrade-Insecure-Requests", "1")
					.method(Connection.Method.GET)
					.timeout(200000).get(); // 设置请求头等信息，模拟人工访问，超时时间可自行设置

			Elements names = doc.select("#m-artist-box li a.s-fc0");
			Elements img = doc.select("img");
            for(Element e : img){
				System.out.println(img.toString());
			}

			for (Element element : names) {

				String mess = "{\"name\":" + "\"" + element.text() + "\"," +
						"\"uid\":" + "\"" + element.attr("href").
						replace("/artist?id=", "").trim() + "\"}";
				System.out.println(mess);
				//FileUtils.saveConToFile(mess, "g://singer.json"); // 可自行写存储信息的代码
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}