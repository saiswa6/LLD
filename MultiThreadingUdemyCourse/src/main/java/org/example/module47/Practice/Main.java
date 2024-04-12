package org.example.module47.Practice;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(2,4,10, TimeUnit.MINUTES,
                new ArrayBlockingQueue<>(6), Executors.defaultThreadFactory(), new ThreadPoolExecutor.DiscardOldestPolicy());

        Future<?> runnableFuture = executor.submit(
                () -> {
                    System.out.println("Runnable Task");
                    try{
                        Thread.sleep(4000);
                    } catch (Exception e){

                    }
                }
        );

        try{
            System.out.println("Runnable get : " + runnableFuture.get());
        } catch (Exception e) {

        }

        List<Integer> runnableList = new ArrayList<>();

        Future<List<Integer>> runnableResult = executor.submit(
                () -> {
                    runnableList.add(666);
                    System.out.println("Runnable with result ");
                }, runnableList
        );

        try{
            System.out.println("Runnable with result list value : " + runnableResult.get().get(0));
        } catch (Exception e) {

        }


        Future<List<Integer>> callableFuture = executor.submit(
                () -> {
                    System.out.println("Callable Task ");
                    List<Integer> callableList = new ArrayList<>();
                    callableList.add(369);
                    return callableList;
                }
        );

        try{
            System.out.println("Callable with result list value : " + callableFuture.get().get(0));
        } catch (Exception e) {

        }

        executor.shutdown();


    }
}
