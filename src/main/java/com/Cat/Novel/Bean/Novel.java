package com.Cat.Novel.Bean;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 小说实体类
 * @author 13001
 *
 */

public class Novel {


	private String id;
	//小说路径
	private String url;
	private String novelName;              //书名
	private String ImgUrl;                 //书面封皮
	private String wirterid;               //写手id
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getNovelName() {
		return novelName;
	}
	public void setNovelName(String novelName) {
		this.novelName = novelName;
	}
	public String getImgUrl() {
		return ImgUrl;
	}
	public void setImgUrl(String imgUrl) {
		ImgUrl = imgUrl;
	}
	public String getWirterid() {
		return wirterid;
	}
	public void setWirterid(String wirterid) {
		this.wirterid = wirterid;
	}
	@Override
	public String toString() {
		return "Novel [id=" + id + ", url=" + url + ", novelName=" + novelName + ", ImgUrl=" + ImgUrl + ", wirterid="
				+ wirterid + "]";
	}
	
	
}
