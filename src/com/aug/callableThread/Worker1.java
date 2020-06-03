package com.aug.callableThread;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;

public class Worker1 implements Callable {

    private CountDownLatch countDownLatch;
    public Worker1(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    @Override
    public Object call() throws Exception {
        Thread.sleep(5000);
        countDownLatch.countDown();
        System.out.println("Worker1");
        return "工人1在工作中....";
    }
}
