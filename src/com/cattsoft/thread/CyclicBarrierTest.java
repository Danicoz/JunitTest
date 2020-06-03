package com.cattsoft.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 功能：使一定数量的线程在栅栏位置处汇集。当线程到达栅栏位置时将调用await方法，
 * 这个方法将阻塞直到所有线程都到达栅栏位置。如果所有线程都到达栅栏位置，
 * 那么栅栏将打开，此时所有的线程都将被释放，而栅栏将被重置以便下次使用。
 * 通俗的讲就是一定数量的线程在同一起点运行。
 * 核心：1、线程组内彼此相互等待，然后大家开始做某件事；
 *      2、这一代结束后开始下一代–重用思想
 * https://blog.csdn.net/J080624/article/details/85261930
 */
public class CyclicBarrierTest {
    private static Logger LOGGER = LoggerFactory.getLogger(CyclicBarrierTest.class);

    public static void main(String[] args) throws InterruptedException {
        //构造器：设置屏障放开前做的事情
        CyclicBarrier barrier3 = new CyclicBarrier(2, () -> {//() -> 相当于new Runnable().run()
            LOGGER.info("屏障放开，[屏障线程]先运行！");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            LOGGER.info("[屏障线程]的事情做完了!");
        });
        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                LOGGER.info(Thread.currentThread().getName() + " 等待屏障放开");
                try {
                    barrier3.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
                LOGGER.info(Thread.currentThread().getName() + "开始干活...干活结束");
            }).start();
        }
    }


}

