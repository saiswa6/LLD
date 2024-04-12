package org.example;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledExecutorExample {
    public static void main(String[] args) {
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

        // Task to run after a delay of 2 seconds
        executorService.schedule(() -> {
            System.out.println("Task 1 executed after 2 seconds");
        }, 2, TimeUnit.SECONDS);

        // Task to run at fixed rate of 3 seconds
        executorService.scheduleAtFixedRate(() -> {
            System.out.println("Task 2 executed at fixed rate of 3 seconds");
        }, 0, 3, TimeUnit.SECONDS);

        // Task to run at fixed delay of 5 seconds
        executorService.scheduleWithFixedDelay(() -> {
            System.out.println("Task 3 executed with fixed delay of 5 seconds after each execution completes");
            try {
                Thread.sleep(1000); // Simulate task execution time
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, 0, 5, TimeUnit.SECONDS);

        // Shutdown the executor after 20 seconds
        executorService.schedule(() -> {
            executorService.shutdown();
            System.out.println("Executor service shutdown");
        }, 20, TimeUnit.SECONDS);
    }
}

