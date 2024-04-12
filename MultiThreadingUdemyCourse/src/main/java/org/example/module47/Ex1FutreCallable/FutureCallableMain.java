package org.example.module47.Ex1FutreCallable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class FutureCallableMain {
    public static void main(String[] args) {
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(3,3,1, TimeUnit.HOURS,
                new ArrayBlockingQueue<>(10), Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());

        //Usecase 1
        Future<?> futureObject1 = poolExecutor.submit(
                () -> {
                    System.out.println("Task1 with Runnable");
                }
        );

        try{
            Object object = futureObject1.get(); // object is null for Runnable, as it do not return anything.
            if(object == null){
                System.out.println("Runnable object is null  ");
            } else {
                System.out.println("Runnable object is not null  ");
            }
        } catch (Exception e){

        }

        System.out.println("=================================================");

        //Usecase 2

        List<Integer> output = new ArrayList<>();
        Future<List<Integer>> futureObject2 = poolExecutor.submit(new MyRunnable(output),output);

        try{
            futureObject2.get();
            //1st way if we have access to output directly
            System.out.println(output.get(0));
            //2nd way get returns result
            List<Integer> result = futureObject2.get();
            System.out.println(result.get(0));
        } catch (Exception e){

        }

        System.out.println("=================================================");

        //Usecase 3


        Future<List<Integer>> futureObject3 = poolExecutor.submit(
                () -> {
                    System.out.println("Task3 with callable");
                    List<Integer> output2 = new ArrayList<>();
                    output2.add(369);
                    return output2;
                }
        );

        try{
            List<Integer> result2 = futureObject3.get();
            System.out.println(result2.get(0));
        } catch (Exception e){

        }

        poolExecutor.shutdown();

        /*
        Task1 with Runnable
        Runnable object is null
        =================================================
        Inside MyRunnable
        Task2 with runnable and result
        300
        300
        =================================================
        Task3 with callable
        369
         */
    }
}
