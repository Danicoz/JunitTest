package com.cattsoft.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 使用4个线程分别计算1-25,26-50,51-75,76-100的值,最后求和。
 */
public class ExecutorServiceDemo {

	public static void main(String[] args) {
		
		List<Future<Integer>> results = new ArrayList<Future<Integer>>();
		ExecutorService exe = Executors.newFixedThreadPool(4);
		
		for(int i = 1; i <= 76; i += 25){
			DoTask task = new DoTask(i, i+24);
			Future<Integer> result = exe.submit(task);
			results.add(result);
		
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		exe.shutdown();
		if(exe.isShutdown()){//4个线程运行后才累加结果
			int sum = 0;
			for(Future<Integer> f : results){
				try {
					sum +=(Integer) f.get();
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (ExecutionException e) {
					e.printStackTrace();
				}
			}
			System.out.println("总值=" + sum);
		}
		
	}	
}

class DoTask implements Callable<Integer>{

	int start = 0;
	int end = 0;
	int sum = 0;

	public DoTask(int start, int end) {
		super();
		this.start = start;
		this.end = end;
	}

	@Override
	public Integer call() throws Exception {
		
		for(int i = start; i <= end; i++){
			sum += i;
		}
		System.out.println("from "+Thread.currentThread().getName()+" sum="+sum);
		return sum;
	}
	
}


