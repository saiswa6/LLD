package org.concurrency.ThreadPoolExecutors.FixedThreadPool.Imp1;

import java.util.concurrent.Executors;

public class ExecutorMain {
    public static void main(String[] args) {
        int fixedPoolSize = 6;

        CustomExecutorService executorService = CustomExecutors.newFixedThreadPool(fixedPoolSize);
        //Executors.newScheduledThreadPool()

        for(int i=0; i< 10; i++) {
            int taskNumber = i;
            executorService.submit(() -> {
                try{
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Task " + taskNumber + " executed by Thread " + Thread.currentThread().getName());
            });
        }

//        try {
//            Thread.sleep(20000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
        executorService.shutdown();


    }
}
