package com.cattsoft.countDownLatch;

import java.util.concurrent.CountDownLatch;

public class Worker2 implements Runnable {
    private CountDownLatch cdl;
    public Worker2(CountDownLatch cdl) {
        this.cdl = cdl;
    }

    @Override
    public void run() {
        System.out.println("����Worker2��ʼ����.....");
        cdl.countDown();
    }
}
