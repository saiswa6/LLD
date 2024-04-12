package org.example.module40.Ex2SuspendResume;


import java.util.Objects;

public class Main {
    public static void main(String[] args) {
        SharedResource sharedResource = new SharedResource();

        System.out.println("Main thread started");
        Thread t1 = new Thread(
                ()-> {
                    System.out.println("Thread 1 calling produce method");
                    sharedResource.produce();
                }
        );

        Thread t2 = new Thread(
                ()-> {
                    System.out.println("Thread 2 calling produce method");
                    sharedResource.produce();
                }
        );

        t1.start();
        t2.start();

        try{
            Thread.sleep(3000);
        } catch (InterruptedException e){
            throw new RuntimeException(e);
        }

        t1.suspend();
        /*
        Main thread started
        Thread 1 calling produce method
        Thread 2 calling produce method
        Lock acquired by Thread-0

        //Program not terminated
         */

        t1.resume();
        /*
        Main thread started
        Thread 1 calling produce method
        Thread 2 calling produce method
        Lock acquired by Thread-0
        Lock released by Thread-0
        Lock acquired by Thread-1
        Lock released by Thread-1

        // AFter t1 resume, t2 able to acquire lock.
         */



    }
}
