package com.cattsoft.thread;

public class ThreadDemo extends Thread {

	private boolean runningFlag = false;

	public ThreadDemo() {
		runningFlag = false;
	}

	public synchronized void setRunningFlag(boolean runningFlag) {
		this.runningFlag = runningFlag;
		if (runningFlag) {
			this.notify();
		} else {
			try {
				System.out.println("线程" + Thread.currentThread().getName()
						+ "开始等待");
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void run() {
		sayHello();
	}

	// 锁池状态
	public synchronized void sayHello() {
		while (true) {
			if (!runningFlag) {
				try {
					System.out.println("线程" + Thread.currentThread().getName()
							+ "开始等待");
					this.wait();// 等待状态
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				try {
					sleep(3000);// 堵塞状态
					System.out.println("线程" + Thread.currentThread().getName()
							+ "任务完成\n");
					setRunningFlag(false); // 让当前线程处于等待任务状态
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void main(String[] args) {
		int i = 10;
		while (i-- > 0) {
			// 新建创建
			ThreadDemo demo = new ThreadDemo();
			demo.setName("demo" + i);
			demo.setRunningFlag(true);
			// 可运行状态,start之后等待cpu获取时间片
			demo.start();
		}

	}

}
