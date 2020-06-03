package com.cattsoft.countDownLatch;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
    /*
    * ���ã�
    * 1��ʹһ���̱߳���ȴ������߳�ִ������ִ��;
    * 2��ԭ��ͨ��������ʵ�֣�ÿ��һ���߳�ִ����ɣ�������ֵ-1��
    * ֱ��ֵΪ0ʱ����ʾ�����߳���ִ����ɣ����ѵȴ��߳�ִ�С�
    *������
    * CountDownLatch(int count)����ʼ������ֵ��һ���Ƕ��̵߳ĸ���
    * await(): ���̻߳ᱻ��������ȴ�ֱ��countֵΪ0�ż���ִ��
    * countDown()��������ֵ-1
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
