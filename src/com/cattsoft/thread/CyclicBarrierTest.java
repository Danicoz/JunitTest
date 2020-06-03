package com.cattsoft.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * ���ܣ�ʹһ���������߳���դ��λ�ô��㼯�����̵߳���դ��λ��ʱ������await������
 * �������������ֱ�������̶߳�����դ��λ�á���������̶߳�����դ��λ�ã�
 * ��ôդ�����򿪣���ʱ���е��̶߳������ͷţ���դ�����������Ա��´�ʹ�á�
 * ͨ�׵Ľ�����һ���������߳���ͬһ������С�
 * ���ģ�1���߳����ڱ˴��໥�ȴ���Ȼ���ҿ�ʼ��ĳ���£�
 *      2����һ��������ʼ��һ���C����˼��
 * https://blog.csdn.net/J080624/article/details/85261930
 */
public class CyclicBarrierTest {
    private static Logger LOGGER = LoggerFactory.getLogger(CyclicBarrierTest.class);

    public static void main(String[] args) throws InterruptedException {
        //���������������Ϸſ�ǰ��������
        CyclicBarrier barrier3 = new CyclicBarrier(2, () -> {//() -> �൱��new Runnable().run()
            LOGGER.info("���Ϸſ���[�����߳�]�����У�");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            LOGGER.info("[�����߳�]������������!");
        });
        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                LOGGER.info(Thread.currentThread().getName() + " �ȴ����Ϸſ�");
                try {
                    barrier3.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
                LOGGER.info(Thread.currentThread().getName() + "��ʼ�ɻ�...�ɻ����");
            }).start();
        }
    }


}

