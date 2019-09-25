package com.Cat.Novel.Bean;

/**
 * 写手实体类
 * @author 13001
 *
 */
public class Wirter {

	private int id;                          //写手id
	private String wirterName;               //写手名字
	private String wirterImg;                //写手照片
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getWirterName() {
		return wirterName;
	}
	public void setWirterName(String wirterName) {
		this.wirterName = wirterName;
	}
	public String getWirterImg() {
		return wirterImg;
	}
	public void setWirterImg(String wirterImg) {
		this.wirterImg = wirterImg;
	}
	@Override
	public String toString() {
		return "Wirter [id=" + id + ", wirterName=" + wirterName + ", wirterImg=" + wirterImg + "]";
	}
	
	
}
