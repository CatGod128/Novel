package com.Cat.Novel.Utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则匹配工具类
 * @author 13001
 *
 */
public class RegexUtils {

	/**
	 * 截取小说名
	 * @param str
	 * @return
	 */
	public String getNovelName(String str) {
		String novelName;  
	    String pattern = "(?<=：)(.)+";		
	    Pattern pa=Pattern.compile(pattern);
	    Matcher matcher=pa.matcher(str);
	    if(matcher.find()) {
	    	System.out.println(matcher.group(0));
	    }
	    return null;
	}
	
	/**
	 * 截取真实章节名
	 * @param str
	 * @return
	 */
	public static String getChapterName(String str) {
		String chapterName="";  
	    String pattern = "(?<=章)(.)+";		
	    Pattern pa=Pattern.compile(pattern);
	    Matcher matcher=pa.matcher(str);
	    if(matcher.find()) {
	    	chapterName=matcher.group(0);	    
	    }
	    return chapterName;
	}
	
	/**
	 * 截取作者名
	 * @param str
	 * @return
	 */
	public static String getWirterName(String str) {
		String wirterName="";  
	    String pattern = "(?<=者)(.)+";		
	    Pattern pa=Pattern.compile(pattern);
	    Matcher matcher=pa.matcher(str);
	    if(matcher.find()) {
	    	wirterName=matcher.group(0);	    
	    }
	    return wirterName;
	}
}
