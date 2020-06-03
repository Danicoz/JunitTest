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

        if( readerInfo.get("ReaderName").toString().contains("6")){//ģ���Ƿ������߳�����
            // get �����������ģ�6�Ƚ�����֮��Ľ��Ҫ�ȴ�6��ɻ�ȡ�����������
            Thread.sleep(2000);
        }

        return registerReader(Thread.currentThread().getName() + "�����Ķ�" + readerInfo.get("ReaderName").toString());
    }
}
