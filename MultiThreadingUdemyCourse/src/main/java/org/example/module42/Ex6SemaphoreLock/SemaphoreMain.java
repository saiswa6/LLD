package org.example.module42.Ex6SemaphoreLock;


public class SemaphoreMain {
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
                    sharedResource.producer();
                }
        );

        Thread t4 = new Thread(
                () -> {
                    sharedResource.producer();
                }
        );

        t1.start();
        t2.start();
        t3.start();
        t4.start();

        /*
        Lock acquired by : Thread-0
        Lock acquired by : Thread-1
        Lock released by : Thread-0
        Lock released by : Thread-1
        Lock acquired by : Thread-3
        Lock acquired by : Thread-2
        Lock released by : Thread-3
        Lock released by : Thread-2
         */
    }
}
