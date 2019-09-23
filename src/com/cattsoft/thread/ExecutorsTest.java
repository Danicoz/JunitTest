package com.cattsoft.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

/**
 * �̳߳��ࣺ
 *  newCachedThreadPool������һ���ɻ����̳߳أ�����̳߳س��ȳ���������Ҫ���������տ����̣߳����޿ɻ��գ����½��̡߳�
 * 	newFixedThreadPool������һ�������̳߳أ��ɿ����߳���󲢷������������̻߳��ڶ����еȴ���
 * 	newScheduledThreadPool������һ�������̳߳أ�֧�ֶ�ʱ������������ִ�С�
 * 	newSingleThreadExecutor������һ�����̻߳����̳߳أ���ֻ����Ψһ�Ĺ����߳���ִ�����񣬱�֤����������ָ��˳��(FIFO,
 * 	LIFO, ���ȼ�)ִ�С�
 *new ThreadPoolExecutor(5, 5, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());
 */
public class ExecutorsTest {

	public static void main(String[] args) {
		ExecutorsTest ect = new ExecutorsTest();

		// ect.testNewCachedThreadPool();

		 ect.testNewFixedThreadPool();

		// ect.testNewScheduledThreadPool();

		// ect.testNewScheduledThreadPool_1();

		// ect.testNewSingleThreadExecutor();
		//ect.testNewSingleThreadExecutor1();
	}

	/*
	 * ����һ���ɻ����̳߳أ�����̳߳س��ȳ���������Ҫ���������տ����̣߳����޿ɻ��գ����½��̡߳�
	 * ��ִ�еڶ�������ʱ��һ�������Ѿ���ɣ��Ḵ��ִ�е�һ��������̣߳�������ÿ���½��̡߳�
	 */
	@Test
	public void testNewCachedThreadPool() {

		ExecutorService es = Executors.newCachedThreadPool();
		for (int i = 0; i < 10; i++) {
			final int index = i;

			try {
				Thread.sleep(i * 400);// ������ʱ���ӡ����̵߳�����

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			es.execute(new Runnable() {

				@Override
				public void run() {
					System.out.println(index + ":"
							+ Thread.currentThread().getName());

				}
			});
		}
	}

	/**
	 * ����һ�������̳߳أ��ɿ����߳���󲢷������������̻߳��ڶ����еȴ��� ÿ��3���ӡ�����߳�����
	 */
	@Test
	public void testNewFixedThreadPool() {

		ExecutorService ex = Executors.newFixedThreadPool(3);

		for (int i = 0; i < 10; i++) {
			final int index = i;
			ex.execute(new Runnable() {

				@Override
				public void run() {
					try {
						System.out.println(index + ":"
								+ Thread.currentThread().getName());
						Thread.sleep(3000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			});
		}
	}

	/**
	 * �ӳټ����߳�
	 */
	@Test
	public void testNewScheduledThreadPool() {
		ScheduledExecutorService st = Executors.newScheduledThreadPool(3);
		st.schedule(new Runnable() {

			@Override
			public void run() {
				System.out.println("delay 3 seconds");
			}

		}, 3, TimeUnit.SECONDS);
	}

	/**
	 * �ӳٲ���ʱ�����߳� �ӳ�2�룬ÿ3������һ��
	 */
	@Test
	public void testNewScheduledThreadPool_1() {

		ScheduledExecutorService ses = Executors.newScheduledThreadPool(3);
		ses.scheduleAtFixedRate(new Runnable() {

			@Override
			public void run() {
				System.out
						.println("delay 3 seconds and excute every 3 seconds!");
				System.out.println(Thread.currentThread().getName());
			}
		}, 2, 3, TimeUnit.SECONDS);
	}

	/**
	 * ����һ�����̻߳����̳߳أ���ֻ����Ψһ�Ĺ����߳���ִ������ ��֤����������ָ��˳��(FIFO, LIFO, ���ȼ�)ִ�С�
	 */
	@Test
	public void testNewSingleThreadExecutor() {

		ExecutorService singleThread = Executors.newSingleThreadExecutor();

		for (int i = 0; i < 10; i++) {
			final int index = i;
			singleThread.execute(new Runnable() {

				@Override
				public void run() {
					System.out.println(index + ":"
							+ Thread.currentThread().getName());
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			});
		}
	}

	@Test
	public void testNewSingleThreadExecutor1() {

		ExecutorService singleThread = Executors.newSingleThreadExecutor();

		for (int i = 0; i < 10; i++) {
			singleThread.execute(new ThreadTest());
		}
	}

	class ThreadTest implements Runnable {

		@Override
		public void run() {
			try {
				Thread.sleep(2000);
				System.out.println(Thread.currentThread().getName());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
