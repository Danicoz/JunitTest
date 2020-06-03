package com.aug.callableThread.libraryCallable;

import java.util.Map;
import java.util.concurrent.Callable;

public class LibraryService implements Callable<String> {

    private LibraryService(){}

    private Map<String,String>readerInfo;
    public LibraryService(Map<String, String> readerInfo) {
        this.readerInfo = readerInfo;
    }

    public String registerReader(String info){
        //System.out.println(info);
        return info;
    }

    @Override
    public String call() throws Exception {

        if( readerInfo.get("ReaderName").toString().contains("6")){//模拟是否进入多线程运行
            // get 方法是阻塞的，6先进来则之后的结果要等待6完成获取结果才能运行
            Thread.sleep(2000);
        }

        return registerReader(Thread.currentThread().getName() + "正在阅读" + readerInfo.get("ReaderName").toString());
    }
}
