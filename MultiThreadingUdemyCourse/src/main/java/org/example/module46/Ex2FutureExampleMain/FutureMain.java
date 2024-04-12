package org.example.module46.Ex2FutureExampleMain;


import java.sql.SQLOutput;
import java.util.concurrent.*;

public class FutureMain {
    public static void main(String[] args) {
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(1,1,1,
                   TimeUnit.HOURS,new ArrayBlockingQueue<>(10), Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());


        Future<?> futureObject = poolExecutor.submit(
                () -> {
                    try{
                        Thread.sleep(8000);
                        System.out.println("This is the task, which thread will be executed");
                    } catch (Exception e){

                    }
                }
        );

        System.out.println("is Done : " + futureObject.isDone());

        try{
            futureObject.get(2,TimeUnit.SECONDS);
        } catch (TimeoutException e) {
            System.out.println("TimeoutException Happened");
        }
        catch (Exception e){

        }

        try{
            futureObject.get();
        } catch (Exception e){

        }

        System.out.println(" is Done : " + futureObject.isDone());
        System.out.println(" is Cancelled : " + futureObject.isCancelled());

        /*
        is Done : false
        TimeoutException Happened
        This is the task, which thread will be executed
        is Done : true
        is Cancelled : false
         */

        poolExecutor.shutdown(); // shutdown is needed , otherwise Thread pool will be alive, program run continously
    }
}
