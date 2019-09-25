package com.Cat.Novel.Bean;

/**
 * 章节实体类
 * @author 13001
 *
 */
public class Chapter {

	private int  id;                   //id
	private String  chapterName;       //章节名称  如：第二百六十七章  天上月
	private String  realName;          //真实名称  如：  天上月
	private String  url;               //路径
	private String content;            //章节内容
	private int  novelId;              //小说id
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getChapterName() {
		return chapterName;
	}
	public void setChapterName(String chapterName) {
		this.chapterName = chapterName;
	}		
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getNovelId() {
		return novelId;
	}
	public void setNovelId(int novelId) {
		this.novelId = novelId;
	}
	@Override
	public String toString() {
		return "Chapter [id=" + id + ", chapterName=" + chapterName + ", realName=" + realName + ", url=" + url
				+ ", content=" + content + ", novelId=" + novelId + "]";
	}

   	
}
