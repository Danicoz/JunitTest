package com.aug.callableThread;

import java.util.Map;
import java.util.concurrent.*;

//get() 方法会阻塞，必须等到执行完才能执行别的任务
//https://blog.csdn.net/NDboy/article/details/85008899
public class BossMain {

    private static CountDownLatch countDownLatch = new CountDownLatch(3);
    private static ExecutorService executor = Executors.newFixedThreadPool(3);
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        Worker1 worker1 = new Worker1(countDownLatch);
        FutureTask<String> futureTask1 = new FutureTask<>(worker1);
        executor.execute(futureTask1);

        Worker2 worker2 = new Worker2(countDownLatch);
        FutureTask<String> futureTask2 = new FutureTask<>(worker2);
        executor.execute(futureTask2);

        Worker3 worker3 = new Worker3(countDownLatch);
        FutureTask<String> futureTask3 = new FutureTask<>(worker3);
        executor.execute(futureTask3);

        countDownLatch.await();

        System.out.println("获取返回值：" + futureTask1.get() + futureTask2.get() + futureTask3.get());

    }
}
