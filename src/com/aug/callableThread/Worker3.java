package com.aug.callableThread;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;

public class Worker3 implements Callable{

    private CountDownLatch countDownLatch;
    public Worker3(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    @Override
    public Object call() throws Exception {
        Thread.sleep(3000);
        countDownLatch.countDown();
        System.out.println("Worker3");
        return "工人3在工作中....";
    }
}
