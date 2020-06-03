package com.cattsoft.countDownLatch;

import java.util.concurrent.CountDownLatch;

/*
* 场景2：
* 实现多个线程开始执行任务的最大并行性，强调的是多个线程在某一时刻同时开始执行。
* 设计思想： 多个线程进行等待主线程发出命令。多个线程await(), 主线程countDown()。
* */
public class CountDownLatchTest2 {
    public static void main(String[] args) {
        CountDownLatch cdl = new CountDownLatch(1);
        for(int i = 0;i < 5; i++){
            new Thread(new Runner(cdl,"Thread-" + i)).start();
        }
        cdl.countDown();//通知其他线程开始运行
    }


}

