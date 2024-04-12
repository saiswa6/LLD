package org.example.module42.Ex4StampedLock1;

public class StampedLock1Main {
    public static void main(String[] args) {
        SharedResource sharedResource = new SharedResource();

        Thread t1 = new Thread(
                () -> {
                    sharedResource.producer();
                }
        );

        Thread t2 = new Thread(
                () -> {
                    sharedResource.producer();
                }
        );

        Thread t3 = new Thread(
                () -> {
                    sharedResource.consume();
                }
        );

        t1.start();
        t2.start();
        t3.start();

        /*
        Read Lock acquired by : Thread-0
        Read Lock acquired by : Thread-1
        Read Lock released by :  Thread-0
        Read Lock released by :  Thread-1
        Write Lock acquired by : Thread-2
        Write Lock released by :  Thread-2
         */
    }
}
