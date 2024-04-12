package org.example.module42.Ex1ReentrantLock;

public class SameObjectReentrantMain {
    public static void main(String[] args) {
        SharedResource sharedResource = new SharedResource();

        Thread t1 = new Thread(
                ()-> {
                    sharedResource.producer();;
                }
        );

        Thread t2 = new Thread(
                ()-> {
                    sharedResource.producer();;
                }
        );

        t1.start();
        t2.start();

        /*
        Reentrant Lock on same object
        =============================
        Lock acquired by : Thread-0
        Lock release by : Thread-0
        Lock acquired by : Thread-1
        Lock release by : Thread-1
         */
    }
}
