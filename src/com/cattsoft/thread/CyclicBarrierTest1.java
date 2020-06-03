package com.cattsoft.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;

/**
 * �����ĸ���һ���������
 */
public class CyclicBarrierTest1 {

    private static Logger LOGGER = LoggerFactory.getLogger(CyclicBarrierTest1.class);

    private static final ThreadPoolExecutor executor = new ThreadPoolExecutor(4,10,60, TimeUnit.MICROSECONDS,new LinkedBlockingDeque<>());
    private static CyclicBarrier cb = new CyclicBarrier(4, () -> {
        LOGGER.info("�ĸ����Ѿ����׼����������...");
    });

    static class GoThread extends Thread{

        private String name;
        public GoThread(String name){
            this.name = name;
        }

        public void run(){
            LOGGER.info(name  + "�Ӽ������...");
            try {
                cb.await();//����դ����ʹ���߳�����
                LOGGER.info(name + "��¥�׳���...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        String[] str1 = {"����","����","����","����"};

        for(String name : str1){
            executor.execute(new GoThread(name));
        }

        try {
            Thread.sleep(3000);
            LOGGER.info("�ĸ��˵��ﳡ�أ���ʼ����...");
            LOGGER.info("�����ĸ��������򣬿�����������...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        while(cb.isBroken()){
            LOGGER.info("�̳߳����жϣ��˳�����...");
        }

        cb.reset();//����դ��

        String[] str2 = {"����","���","�ױ�","����"};
        for(String name : str2){
            executor.execute(new GoThread(name));
        }

        try {
            Thread.sleep(3000);
            LOGGER.info("�˸���һ�������������...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executor.shutdown();
    }
}
