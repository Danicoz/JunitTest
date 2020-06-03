package com.cattsoft.countDownLatch;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
    /*
    * 作用：
    * 1、使一个线程必须等待其他线程执行完再执行;
    * 2、原理通过计数器实现，每当一个线程执行完成，计数器值-1，
    * 直到值为0时，表示所有线程已执行完成，唤醒等待线程执行。
    *方法：
    * CountDownLatch(int count)：初始化计数值，一般是多线程的个数
    * await(): 主线程会被挂起，它会等待直到count值为0才继续执行
    * countDown()：计数器值-1
    * */
public class CountDownLatchTest {
    public static void main(String[] args) {
        ExecutorService pool = Executors.newCachedThreadPool();
        CountDownLatch cdl = new CountDownLatch(100);
        for (int i = 0; i < 100; i++) {
            WorkerRunnable wk = new WorkerRunnable(cdl);
            pool.execute(wk);
        }
    }
}


class WorkerRunnable implements Runnable {
    private CountDownLatch latch = null;

    public WorkerRunnable(CountDownLatch count) {
        this.latch = count;
    }

    @Override
    public void run() {
        try {
            synchronized (latch) {
                latch.countDown();
                System.out.println("thread counts = " + latch.getCount());
            }
            latch.await();
            System.out.println("thread1 counts = " + latch.getCount());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
