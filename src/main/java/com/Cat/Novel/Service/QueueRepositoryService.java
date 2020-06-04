package com.Cat.Novel.Service;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.springframework.stereotype.Service;

import com.mysql.jdbc.StringUtils;



/**
 * url路径仓库
 * @author 13001
 *
 */
@Service
public class QueueRepositoryService {

	/**
	 *高优先级队列
	 */
	private Queue<String> highLevelQueue=new ConcurrentLinkedQueue<String>();
	/**
	 * 低优先级队列
	 */
	private Queue<String> lowLevelQueue=new ConcurrentLinkedQueue<String>();
	public String poll() {
		String url=this.highLevelQueue.poll();
		if(StringUtils.isNullOrEmpty(url)) {
			url=this.lowLevelQueue.poll();
		}
		return url;
	};
    
	public void addHighLevel(String url) {
		this.highLevelQueue.add(url);
	};
	
	public void addLowLevel(String url) {
		this.lowLevelQueue.add(url);
	};

	public int getSize(){
		System.out.println(this.lowLevelQueue.size()+this.lowLevelQueue.size());
		return (this.lowLevelQueue.size()+this.lowLevelQueue.size());
	}
}

