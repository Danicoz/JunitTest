package com.cattsoft.countDownLatch;

import java.util.concurrent.CountDownLatch;

public class Worker3 implements Runnable {
    private CountDownLatch cdl;
    public Worker3(CountDownLatch cdl) {
        this.cdl = cdl;
    }

    @Override
    public void run() {
        System.out.println("����Worker3��ʼ����.....");
        cdl.countDown();
    }
}
