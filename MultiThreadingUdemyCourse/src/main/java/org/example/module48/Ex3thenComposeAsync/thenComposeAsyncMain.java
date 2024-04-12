package org.example.module48.Ex3thenComposeAsync;

import java.util.concurrent.*;

public class thenComposeAsyncMain {
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
                        return "Hello ";
                    },
                    poolExecutor).thenComposeAsync((String val) ->
                    {
                        System.out.println("Thread name of thenApply is : " + Thread.currentThread().getName());
                        return CompletableFuture.supplyAsync(() -> val + "world");
                    });


            System.out.println(asyncTask1.get()); // wait till task is completed
        } catch (Exception e) {

        }
        /*
        asyncTask1 Task completed
        Thread name of SupplyAsyncTask1 is : pool-1-thread-1
        Thread name of thenApply is : ForkJoinPool.commonPool-worker-1
        Hello world
         */

    }
}
