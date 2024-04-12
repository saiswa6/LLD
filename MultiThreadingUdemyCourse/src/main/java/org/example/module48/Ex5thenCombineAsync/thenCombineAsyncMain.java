package org.example.module48.Ex5thenCombineAsync;

import java.util.concurrent.*;

public class thenCombineAsyncMain {
    public static void main(String[] args) {
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(1, 1, 5, TimeUnit.MINUTES,
                new ArrayBlockingQueue<>(6), Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());

        try {
            CompletableFuture<Integer> asyncTask1 = CompletableFuture.supplyAsync(() ->
                    {
                        System.out.println("asyncTask1 completed");
                        return 66;
                    },
                    poolExecutor);

            System.out.println("asyncTask1 result " + asyncTask1.get()); // wait till task is completed


            CompletableFuture<String> asyncTask2 = CompletableFuture.supplyAsync(() ->
                    {
                        System.out.println("asyncTask2 completed");
                        return "Sai ";
                    },
                    poolExecutor);

            System.out.println("asyncTask2 result " + asyncTask2.get()); // wait till task is completed


            CompletableFuture<String> combineObject = asyncTask1.thenCombine(asyncTask2, (Integer val1, String val2) -> val1 + val2);


            System.out.println("asyncTasks combine result " + combineObject.get());
        } catch (Exception e){

        }
        poolExecutor.shutdown();

        /*
        asyncTask1 completed
        asyncTask1 result 66
        asyncTask2 completed
        asyncTask2 result Sai
        asyncTasks combine result 66Sai
         */

    }
}
