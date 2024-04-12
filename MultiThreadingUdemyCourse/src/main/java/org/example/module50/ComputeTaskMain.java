package org.example.module50;

import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;

public class ComputeTaskMain {
    public static void main(String[] args) {

        ForkJoinPool pool = ForkJoinPool.commonPool();
        Future<Integer> futureObject  = pool.submit(new ComputeTask(0, 100));
        try{
            System.out.println("Sum : " + futureObject.get());
        } catch (Exception e) {

        }
        //Executors.newFixedThreadPool();
    }
    /*
    Sum : 5050
     */
}
