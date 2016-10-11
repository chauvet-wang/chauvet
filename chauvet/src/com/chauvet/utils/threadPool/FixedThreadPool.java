package com.chauvet.utils.threadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/***
 * 
 * 创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待。
 * 
 * 定长线程池的大小最好根据系统资源进行设置。
 * 如:Runtime.getRuntime().availableProcessors() (java虚拟机可用的处理器个数)
 * 
 * 一般需要根据任务的类型来配置线程池大小：
　*　如果是CPU密集型任务，就需要尽量压榨CPU，参考值可以设为 NCPU+1.
　*　如果是IO密集型任务，参考值可以设置为2*NCPU.
 * 
 * @author WXW
 *
 */
public class FixedThreadPool {
//	ExecutorService fixedThreadPool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
	public static void main(String[] args) {
		//因为线程池大小为3，每个任务输出index后sleep 2秒，所以每两秒打印3个数字。
		ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);  
		for (int i = 0; i < 10; i++) {
			final int index = i;  
			fixedThreadPool.execute(new Runnable() {
				public void run() {
				try {
					System.out.println(index);  
					Thread.sleep(1000);  
				} catch (InterruptedException e) {  
					e.printStackTrace();  
				}  
			}  
			});  
		}  
	}  
}
