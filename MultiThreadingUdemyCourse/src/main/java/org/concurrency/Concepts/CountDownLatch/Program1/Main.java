package org.concurrency.Concepts.CountDownLatch.Program1;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(5);
        ExecutorService service = Executors.newSingleThreadExecutor();

        for(int i = 0; i < 5; i++) {
            service.submit(new Latch(i, latch));
        }

        try{
            latch.await();// Main Thread is going to execute until all threads are finished.
        } catch (InterruptedException e){
            e.printStackTrace();
        }

        System.out.println("All threads have been finished");
        service.shutdown();
    }

    /*
    Thread  with ID 0 starts working...
    Thread  with ID 1 starts working...
    Thread  with ID 2 starts working...
    Thread  with ID 3 starts working...
    Thread  with ID 4 tarts working...
    All threads have been finished

     */
    // If count down latch is more than no of threads, then latch on main thread keeps on waiting. Thst's why
    // generally latch should be less than no of threads.in this case, await(3 seconds) can be used.
}
