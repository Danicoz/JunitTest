package com.aug.callableThread.libraryCallable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

public class Reader {
    private static final ExecutorService executor = new ThreadPoolExecutor(5, 6,
            5000L, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(20), new ThreadPoolExecutor.DiscardPolicy());

    private static List<FutureTask<String>> futureTasks = new ArrayList<FutureTask<String>>();

    public static void main(String[] args) throws InterruptedException {
//        Map<String,String>readerInfo = null;
        for(int i = 1; i<= 10; i++){
            Map<String,String>readerInfo = new HashMap<>();
            readerInfo.put("ReaderName", "ReaderName" + i);
            FutureTask<String>futureTask = new FutureTask<>(new LibraryService(readerInfo));
            executor.submit(futureTask);
            futureTasks.add(futureTask);
        }

        //Thread.sleep(3000);
        for(FutureTask futureTask : futureTasks){
            try {
                System.out.println(futureTask.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }


    }

}
