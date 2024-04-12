package org.example.module46.Ex1ThreadPoolExecutor;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolMain {

    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 4, 10, TimeUnit.MINUTES,
                new ArrayBlockingQueue<>(2),new CustomThreadFactory(), new CustomRejectHandler());

        //executor.allowCoreThreadTimeOut(true);

        for(int i=1; i<=7;i++){
            executor.submit(() -> {
                        try{
                            Thread.sleep(4000);
                            System.out.println("Task processed by " + Thread.currentThread().getName());
                        } catch (Exception e){
                            throw new RuntimeException(e);
                        }
                    });
        }


        executor.shutdown();

        /*
        For 4 tasks
        =============
        Task processed by Thread-1
        Task processed by Thread-0
        Task processed by Thread-1
        Task processed by Thread-0

         */

        /*
        For 5 tasks
        ===================
        Task processed by Thread-1
        Task processed by Thread-0
        Task processed by Thread-2  -- New thread is created as min threads and queue are full
        Task processed by Thread-1
        Task processed by Thread-0
         */

        /*
        For 7 tasks - 1 task is rejected because max threads and queue are full
        ===========
        Task Rejected : java.util.concurrent.FutureTask@31befd9f[Not completed, task = java.util.concurrent.Executors$RunnableAdapter@13221655[Wrapped task = org.example.module46.Ex1ThreadPoolExecutor.ThreadPoolMain$$Lambda$1/0x0000013735000400@2f2c9b19]]
        Task processed by Thread-3
        Task processed by Thread-2
        Task processed by Thread-1
        Task processed by Thread-0
        Task processed by Thread-0
        Task processed by Thread-3
         */
    }
}


