package com.chauvet.utils.threadPool.exmple;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MyTaskTest {
	public static void main(String[] args) {
		/***
		 * 核心池的大小（即线程池中的线程数目大于这个参数时，提交的任务会被放进任务缓存队列） = 5
		 * 线程池最大能容忍的线程数  = 10
		 * 线程没有任务执行时最多保持多久时间会终止  = 200
		 * 线程存货时间 
		 * 一个阻塞队列，用来存储等待执行的任务
		 * 
		 * 
		 * 一般不直接使用ThreadPoolExecutor，而是使用Executors类中提供的几个静态方法来创建线程池：
		 * Executors.newCachedThreadPool();        //创建一个缓冲池，缓冲池容量大小为Integer.MAX_VALUE
		 * Executors.newSingleThreadExecutor();   //创建容量为1的缓冲池
		 * Executors.newFixedThreadPool(int);    //创建固定容量大小的缓冲池
		 * 
		 */
		ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10, 200, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(5));
		for (int i = 0; i < 15; i++) {
			MyTask task = new MyTask(i);
			executor.execute(task);
			System.out.println("线程池中线程数目："+executor.getPoolSize()+"，队列中等待执行的任务数目："+executor.getQueue().size()+"，已执行玩别的任务数目："+executor.getCompletedTaskCount());
		}
		executor.shutdown();
	}
}
