package com.aug.callableThread;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;

public class Worker2 implements Callable {

    private CountDownLatch countDownLatch;
    public Worker2(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    @Override
    public Object call() throws Exception {
        Thread.sleep(3000);
        countDownLatch.countDown();
        System.out.println("Worker2");
        return "工人2在工作中....";
    }
}
