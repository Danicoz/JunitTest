package com.cattsoft.countDownLatch;

import java.util.concurrent.CountDownLatch;

/*
* ����2��
* ʵ�ֶ���߳̿�ʼִ�������������ԣ�ǿ�����Ƕ���߳���ĳһʱ��ͬʱ��ʼִ�С�
* ���˼�룺 ����߳̽��еȴ����̷߳����������߳�await(), ���߳�countDown()��
* */
public class CountDownLatchTest2 {
    public static void main(String[] args) {
        CountDownLatch cdl = new CountDownLatch(1);
        for(int i = 0;i < 5; i++){
            new Thread(new Runner(cdl,"Thread-" + i)).start();
        }
        cdl.countDown();//֪ͨ�����߳̿�ʼ����
    }


}

