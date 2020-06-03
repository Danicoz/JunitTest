package com.cattsoft.countDownLatch;

import java.util.concurrent.CountDownLatch;
/*
* 场景1：主线程需要等待其他线程加载完成才运行
* */
public class CountDownLatchTest1 {
    public static void main(String[] args) {
        try {
            CountDownLatch cdl = new CountDownLatch(3);
            Thread t = new Thread(new Worker1(cdl));
            t.start();

            new Thread(new Worker2(cdl)).start();
            new Thread(new Worker3(cdl)).start();

            cdl.await();
            System.out.println("等待工人工作完成后，工头开始工作.....");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
