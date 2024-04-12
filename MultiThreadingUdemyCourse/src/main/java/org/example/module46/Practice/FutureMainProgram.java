package org.example.module46.Practice;

import java.util.concurrent.*;

public class FutureMainProgram {
    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 4, 10, TimeUnit.MINUTES
                , new ArrayBlockingQueue<>(5), Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());

        Future<?> future = executor.submit(
                () -> {
                    try{
                        Thread.sleep(8000);
                    } catch (Exception e) {

                    }
                }
        );

        System.out.println("Future status : " + future.isDone());

        System.out.println("Future cancellation status : " + future.isCancelled());

        try{
            future.get();
        } catch (Exception e) {

        }
        System.out.println("Future get status : " + future.isDone());


        executor.shutdown();

    }
}
