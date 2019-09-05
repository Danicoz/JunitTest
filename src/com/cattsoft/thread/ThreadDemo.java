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
				System.out.println("�߳�" + Thread.currentThread().getName()
						+ "��ʼ�ȴ�");
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

	// ����״̬
	public synchronized void sayHello() {
		while (true) {
			if (!runningFlag) {
				try {
					System.out.println("�߳�" + Thread.currentThread().getName()
							+ "��ʼ�ȴ�");
					this.wait();// �ȴ�״̬
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				try {
					sleep(3000);// ����״̬
					System.out.println("�߳�" + Thread.currentThread().getName()
							+ "�������\n");
					setRunningFlag(false); // �õ�ǰ�̴߳��ڵȴ�����״̬
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void main(String[] args) {
		int i = 10;
		while (i-- > 0) {
			// �½�����
			ThreadDemo demo = new ThreadDemo();
			demo.setName("demo" + i);
			demo.setRunningFlag(true);
			// ������״̬,start֮��ȴ�cpu��ȡʱ��Ƭ
			demo.start();
		}

	}

}
