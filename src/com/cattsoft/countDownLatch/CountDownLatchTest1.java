package com.cattsoft.countDownLatch;

import java.util.concurrent.CountDownLatch;
/*
* ����1�����߳���Ҫ�ȴ������̼߳�����ɲ�����
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
            System.out.println("�ȴ����˹�����ɺ󣬹�ͷ��ʼ����.....");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
