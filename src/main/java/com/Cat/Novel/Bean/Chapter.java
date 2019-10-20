package com.Cat.Novel.Bean;

/**
 * 章节实体类
 * @author 13001
 *
 */
public class Chapter {

	private String  id;                 //id
	private String  chapterName;       //章节名称  如：第二百六十七章  天上月
	private String  realName;          //真实名称  如：  天上月
	private String  url;               //路径
	private String content;            //章节内容
	private String  novelId;           //小说id
    private String  privousId;        //上一章
	private String  nextId;           //下一章
	private int     orderNum;             //用以排序

	public String getPrivousId() {
		return privousId;
	}

	public void setPrivousId(String privousId) {
		this.privousId = privousId;
	}

	public String getNextId() {
		return nextId;
	}

	public void setNextId(String nextId) {
		this.nextId = nextId;
	}

	public int getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}



	public String  getId() {
		return id;
	}
	public void setId(String  id) {
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
	public String getNovelId() {
		return novelId;
	}
	public void setNovelId(String  novelId) {
		this.novelId = novelId;
	}

	@Override
	public String toString() {
		return "Chapter{" +
				"id='" + id + '\'' +
				", chapterName='" + chapterName + '\'' +
				", realName='" + realName + '\'' +
				", url='" + url + '\'' +
				", content='" + content + '\'' +
				", novelId='" + novelId + '\'' +
				", privousId='" + privousId + '\'' +
				", nextId='" + nextId + '\'' +
				", orderNUm=" + orderNum +
				'}';
	}
}
