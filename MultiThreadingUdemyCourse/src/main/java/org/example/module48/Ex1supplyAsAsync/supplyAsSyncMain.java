package org.example.module48.Ex1supplyAsAsync;

import java.util.concurrent.*;

public class supplyAsSyncMain {
    public static void main(String[] args) {
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(1,1,5, TimeUnit.MINUTES,
                new ArrayBlockingQueue<>(6), Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());

        try {
            CompletableFuture<String> asyncTask1 = CompletableFuture.supplyAsync(() ->
                    {

//                        Thread.sleep(3000);
                        //This is task which need to completed by thread
                        System.out.println("Task completed");

                        return "Task completed";
                    },
                    poolExecutor);

            System.out.println(asyncTask1.get()); // wait till task is completed
        }
        catch (Exception e){

        }

        /*
        Task completed
        Task completed
         */
    }
}
