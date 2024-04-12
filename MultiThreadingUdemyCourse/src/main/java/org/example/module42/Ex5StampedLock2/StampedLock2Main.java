package org.example.module42.Ex5StampedLock2;

public class StampedLock2Main {
    public static void main(String[] args) {
        SharedResource sharedResource = new SharedResource();

        Thread t1 = new Thread(
                () -> {
                    sharedResource.producer();
                }
        );


        Thread t2 = new Thread(
                () -> {
                    sharedResource.consume();
                }
        );

        t1.start();
        //t2.start();


        /*
        When Thread 2 starts, it interrupts the lock and modifies alue. SO, t1 detects this by stamp validation and rollback
        Taken Optimistic Read
        Write Lock acquired by : Thread-1
        Write Lock released by :  Thread-1
        rollback of work
         */

        /*
        When no thread 2, thread 1 - updates value successfully
        Taken Optimistic Read
        Updated a value successfully
         */
    }
}
