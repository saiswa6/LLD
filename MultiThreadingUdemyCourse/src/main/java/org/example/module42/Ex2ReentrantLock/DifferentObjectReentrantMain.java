package org.example.module42.Ex2ReentrantLock;

import java.util.concurrent.locks.ReentrantLock;

public class DifferentObjectReentrantMain {
    public static void main(String[] args) {
        SharedResource sharedResource = new SharedResource();
        SharedResource sharedResource2 = new SharedResource();

        ReentrantLock reentrantLock = new ReentrantLock();

        Thread t1 = new Thread(
                ()-> {
                    sharedResource.producer(reentrantLock);;
                }
        );

        Thread t2 = new Thread(
                ()-> {
                    sharedResource2.producer(reentrantLock);;
                }
        );

        t1.start();
        t2.start();

        /*
        Reentrant Lock on Different threads having different objects of same class
        =============================
        Lock acquired by : Thread-1
        Lock release by : Thread-1
        Lock acquired by : Thread-0
        Lock release by : Thread-0

         */
    }
}
