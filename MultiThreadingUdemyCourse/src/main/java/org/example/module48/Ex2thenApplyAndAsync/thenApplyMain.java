package org.example.module48.Ex2thenApplyAndAsync;

import java.util.concurrent.*;

public class thenApplyMain {
    public static void main(String[] args) {
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(5, 6, 5, TimeUnit.MINUTES,
                new ArrayBlockingQueue<>(6), Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());

        try {
            CompletableFuture<String> asyncTask1 = CompletableFuture.supplyAsync(() ->
                    {
                        //This is task which need to completed by thread
                        System.out.println("asyncTask1 Task completed");
                        try {
                            Thread.sleep(4000);
                        } catch (Exception e) {

                        }
                        System.out.println("Thread name of SupplyAsyncTask1 is : " + Thread.currentThread().getName());
                        return "Concept";
                    },
                    poolExecutor).thenApply((String val) ->
                    {
                        System.out.println("Thread name of thenApply is : " + Thread.currentThread().getName());
                        return "Coding";
                    }
            );

            System.out.println(asyncTask1.get()); // wait till task is completed
        } catch (Exception e) {

        }

        /*
        asyncTask1 Task completed
        Thread name of SupplyAsyncTask1 is : pool-1-thread-1
        Thread name of thenApply is : pool-1-thread-1  // same thread used for thenApply
        Coding
         */

        try {
            CompletableFuture<String> asyncTask2 = CompletableFuture.supplyAsync(() ->
                    {
                        //This is task which need to completed by thread
                        System.out.println("asyncTask2 Task completed");
                        try {
                            Thread.sleep(6000);
                        } catch (Exception e) {

                        }
                        System.out.println("Thread name of SupplyAsyncTask2 is : " + Thread.currentThread().getName());
                        return "Sairam";
                    },
                    poolExecutor).thenApplyAsync((String val) ->
                    {
                        System.out.println("Thread name of thenApplyAsync is : " + Thread.currentThread().getName());
                        return val + " Ganapati";
                    } // If no thread pool provided, it takes thread from fork join pool, TPE can also provided.
            );

            System.out.println(asyncTask2.get()); // waits till task is completed
        } catch (Exception e) {

        }

        /*
        asyncTask1 Task completed
        Thread name of SupplyAsyncTask1 is : pool-1-thread-1
        Thread name of thenApply is : pool-1-thread-1
        Coding

        ///////////////////////////////////////////////////////////////

        asyncTask2 Task completed
        Thread name of SupplyAsyncTask2 is : pool-1-thread-2
        Thread name of thenApplyAsync is : ForkJoinPool.commonPool-worker-1
        Sairam Ganapati
         */
    }
}
