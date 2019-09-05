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
	 * ����1��
	 * 		ʹ��Callable+Future ��ȡִ�н��
	 */
	public void testCallableAndFuture(){
		ExecutorService exe = Executors.newCachedThreadPool();
		
		Future<Integer> result = exe.submit(new Task());
		exe.shutdown();//�����ڽ��������߳�
		//exe.submit(new Task());�쳣
		
		boolean isTerminated = exe.isTerminated();
		boolean isShutdown = exe.isShutdown();
		System.out.println(isTerminated + ":" + isShutdown);//false:true
		
		
		//����һ��ʱ����ӽ�������
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		
//		boolean isTerminated = exe.isTerminated();//ֻ���ύ���ӽ�����ɺ�Ż᷵��true
//		boolean isShutdown = exe.isShutdown();//ֻҪ����Shutdown�ͻ᷵��true
//		System.out.println(isTerminated + ":" + isShutdown);//true:true
		
		
		try {
			Integer num = result.get();
			System.out.println("���صĽ����" + num);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}	
	}
	
	/*
	 * ����2��
	 * 		Callable+FutureTask ��ȡ���
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
			System.out.println("���صĽ����" + num);
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
		System.out.println("�ӽ��̼����������أ�");
		
		int sum = 0;
		for(int i = 0; i <= 100; i++){
			sum += i;
		}
		return sum;	}
	
}