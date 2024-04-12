package org.example.module40.Ex2SuspendResume;

public class JoinMain {
    public static void main(String[] args) {
        SharedResource sharedResource = new SharedResource();

        System.out.println("Main thread started");
        Thread t1 = new Thread(
                ()-> {
                    System.out.println("Thread 1 calling produce method");
                    sharedResource.produce();
                }
        );

        t1.start();
        /*
        Without Join
        ============
        Main thread started
        Main thread is finishing its work
        Thread 1 calling produce method
        Lock acquired by Thread-0
        Lock released by Thread-0
         */

        try {
            t1.join();
        } catch (InterruptedException e){
            throw  new RuntimeException(e);
        }
        /*
        With Join
        =========
        Main thread started
        Thread 1 calling produce method
        Lock acquired by Thread-0
        Lock released by Thread-0
        Main thread is finishing its work
         */
        System.out.println("Main thread is finishing its work");
    }
}
