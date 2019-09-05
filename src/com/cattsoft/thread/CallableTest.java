package com.cattsoft.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class CallableTest {

	public static void main(String[] args) {
		CallableTest call = new CallableTest();
		call.testCallableAndFuture();
//		call.testCallableAndFutureTask();
	}
	
	/*
	 * 方法1：
	 * 		使用Callable+Future 获取执行结果
	 */
	public void testCallableAndFuture(){
		ExecutorService exe = Executors.newCachedThreadPool();
		
		Future<Integer> result = exe.submit(new Task());
		exe.shutdown();//不会在接收其他线程
		//exe.submit(new Task());异常
		
		boolean isTerminated = exe.isTerminated();
		boolean isShutdown = exe.isShutdown();
		System.out.println(isTerminated + ":" + isShutdown);//false:true
		
		
		//休眠一段时间给子进程运行
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		
//		boolean isTerminated = exe.isTerminated();//只有提交的子进程完成后才会返回true
//		boolean isShutdown = exe.isShutdown();//只要调用Shutdown就会返回true
//		System.out.println(isTerminated + ":" + isShutdown);//true:true
		
		
		try {
			Integer num = result.get();
			System.out.println("返回的结果：" + num);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}	
	}
	
	/*
	 * 方法2：
	 * 		Callable+FutureTask 获取结果
	 */
	public void testCallableAndFutureTask(){
		ExecutorService exe = Executors.newCachedThreadPool();
		
		FutureTask<Integer> futureTask = new FutureTask<Integer>(new Task());
		exe.submit(futureTask);
		exe.shutdown();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		
		try {
			Integer num = futureTask.get();
			System.out.println("返回的结果：" + num);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		
	}
}

class Task implements Callable<Integer>{

	@Override
	public Integer call() throws Exception {
		System.out.println("子进程计算结果并返回！");
		
		int sum = 0;
		for(int i = 0; i <= 100; i++){
			sum += i;
		}
		return sum;	}
	
}