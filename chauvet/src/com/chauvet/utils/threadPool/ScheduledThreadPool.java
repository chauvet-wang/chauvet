package com.chauvet.utils.threadPool;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/***
 * 
 * 创建一个定长线程池，支持定时及周期性任务执行。
 * 
 * @author WXW
 *
 */
public class ScheduledThreadPool {
//	ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);  
	public static void main(String[] args) {
//		testDelay();
		testDelayRepart();
	}
	
	/***
	 * 延迟3秒执行
	 */
	public static void testDelay(){
		ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);  
		scheduledThreadPool.schedule(new Runnable() {
			public void run() {  
				System.out.println("3秒后执行，只执行一次");  
			}  
		}, 3, TimeUnit.SECONDS);
	}
	
	/***
	 * 定期执行
	 * 延迟1秒后每3秒执行一次。
	 */
	public static  void testDelayRepart(){
		ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);  
		scheduledThreadPool.scheduleAtFixedRate(new Runnable() {  
			public void run() {  
				System.out.println("延迟1秒后每3秒执行一次。");  
			}  
		}, 1, 3, TimeUnit.SECONDS);
	}  
	
}
