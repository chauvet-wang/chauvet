package com.chauvet.utils.threadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/***
 * 
 * 创建一个单线程化的线程池，它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序(FIFO, LIFO, 优先级)执行。
 * 
 * @author WXW
 *
 */
public class SingleThreadExecutor {

	ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
	
	
	public static void main(String[] args) {
		testSingleThread();
	}  
	
	/***
	 * 结果依次输出，相当于顺序执行各个任务。
	 */
	public static void testSingleThread(){
		ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
		for (int i = 0; i < 10; i++) {
			final int index = i;
			singleThreadExecutor.execute(new Runnable() {
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
