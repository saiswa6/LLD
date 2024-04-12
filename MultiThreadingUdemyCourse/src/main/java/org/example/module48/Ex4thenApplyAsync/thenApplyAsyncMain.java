package org.example.module48.Ex4thenApplyAsync;

import java.util.concurrent.*;

public class thenApplyAsyncMain {
    public static void main(String[] args) {
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(1, 1, 5, TimeUnit.MINUTES,
                new ArrayBlockingQueue<>(6), Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());

        try {
            CompletableFuture<Void> asyncTask1 = CompletableFuture.supplyAsync(() ->
                    {
                        //This is task which need to completed by thread
                        System.out.println("Task completed");

                        return "hell0";
                    },
                    poolExecutor).thenAccept((String val) -> {
                System.out.println("Printing value : " + val);
            });

            System.out.println(asyncTask1.get()); // wait till task is completed
        } catch (Exception e) {

        }

        /*
        Task completed
        Printing value : hello
        null
         */
    }
}
