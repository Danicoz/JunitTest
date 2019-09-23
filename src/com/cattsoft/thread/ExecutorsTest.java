package com.cattsoft.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

/**
 * 线程池类：
 *  newCachedThreadPool：创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程。
 * 	newFixedThreadPool：创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待。
 * 	newScheduledThreadPool：创建一个定长线程池，支持定时及周期性任务执行。
 * 	newSingleThreadExecutor：创建一个单线程化的线程池，它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序(FIFO,
 * 	LIFO, 优先级)执行。
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
	 * 创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程。
	 * 当执行第二个任务时第一个任务已经完成，会复用执行第一个任务的线程，而不用每次新建线程。
	 */
	@Test
	public void testNewCachedThreadPool() {

		ExecutorService es = Executors.newCachedThreadPool();
		for (int i = 0; i < 10; i++) {
			final int index = i;

			try {
				Thread.sleep(i * 400);// 不休眠时会打印多个线程的名称

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
	 * 创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待。 每隔3秒打印三个线程名称
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
	 * 延迟加载线程
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
	 * 延迟并定时加载线程 延迟2秒，每3秒运行一次
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
	 * 创建一个单线程化的线程池，它只会用唯一的工作线程来执行任务， 保证所有任务按照指定顺序(FIFO, LIFO, 优先级)执行。
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
