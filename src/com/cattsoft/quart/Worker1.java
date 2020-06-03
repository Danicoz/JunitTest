package com.cattsoft.quart;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

public class Worker1 {

    private static Logger logger = LoggerFactory.getLogger(Worker1.class);
    public  void work(){
        try {
            Thread.yield();
            for(int i = 0;i < 1000000; i++){
               logger.info("工人1开始工作..." + new Date());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
