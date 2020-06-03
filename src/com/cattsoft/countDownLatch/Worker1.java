package com.cattsoft.countDownLatch;

import java.util.concurrent.CountDownLatch;

public class Worker1 implements Runnable{
    private CountDownLatch cdl;
    public Worker1(CountDownLatch cdl){
        this.cdl = cdl;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println("����Worker1��ʼ����.....");
        }
        cdl.countDown();
    }
}

