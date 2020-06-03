package com.cattsoft.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;

/**
 * 宿舍四个人一起出发打球
 */
public class CyclicBarrierTest1 {

    private static Logger LOGGER = LoggerFactory.getLogger(CyclicBarrierTest1.class);

    private static final ThreadPoolExecutor executor = new ThreadPoolExecutor(4,10,60, TimeUnit.MICROSECONDS,new LinkedBlockingDeque<>());
    private static CyclicBarrier cb = new CyclicBarrier(4, () -> {
        LOGGER.info("四个人已经会合准备出发打球...");
    });

    static class GoThread extends Thread{

        private String name;
        public GoThread(String name){
            this.name = name;
        }

        public void run(){
            LOGGER.info(name  + "从家里出发...");
            try {
                cb.await();//设置栅栏，使该线程阻塞
                LOGGER.info(name + "从楼底出发...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        String[] str1 = {"张三","李四","王五","刘凯"};

        for(String name : str1){
            executor.execute(new GoThread(name));
        }

        try {
            Thread.sleep(3000);
            LOGGER.info("四个人到达场地，开始打球...");
            LOGGER.info("又来四个人来打球，可以来场比赛...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        while(cb.isBroken()){
            LOGGER.info("线程出现中断，退出程序...");
        }

        cb.reset();//重置栅栏

        String[] str2 = {"王二","洪光","雷兵","赵三"};
        for(String name : str2){
            executor.execute(new GoThread(name));
        }

        try {
            Thread.sleep(3000);
            LOGGER.info("八个人一起打球，来场比赛...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executor.shutdown();
    }
}
