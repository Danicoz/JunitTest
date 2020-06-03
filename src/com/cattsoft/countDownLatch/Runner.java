package com.cattsoft.countDownLatch;

import java.util.concurrent.CountDownLatch;

public class Runner implements Runnable {
    private CountDownLatch cdl;
    private String threadName;
    public Runner(CountDownLatch cdl, String threadName) {
        this.cdl = cdl;
        this.threadName = threadName;
    }

    @Override
    public void run() {
        try {
            cdl.await();
            System.out.println("�߳�" + threadName + "��ʼ����....");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
