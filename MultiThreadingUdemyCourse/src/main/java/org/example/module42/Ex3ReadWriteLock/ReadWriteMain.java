package org.example.module42.Ex3ReadWriteLock;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteMain {
    public static void main(String[] args) {
        SharedResource sharedResource1 = new SharedResource();
        SharedResource sharedResource2 = new SharedResource();
        ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
        //ReadWriteLock lock =  new ReentrantReadWriteLock();

        Thread t1 = new Thread(
                () -> {
                    sharedResource1.producer(readWriteLock);
                }
        );

        Thread t2 = new Thread(
                () -> {
                    sharedResource2.producer(readWriteLock);
                }
        );

        SharedResource sharedResource3 = new SharedResource();

        Thread t3 = new Thread(
                () -> {
                    sharedResource3.consume(readWriteLock);
                }
        );

        SharedResource sharedResource4 = new SharedResource();

        Thread t4 = new Thread(
                () -> {
                    sharedResource4.consume(readWriteLock);
                }
        );
        SharedResource sharedResource5 = new SharedResource();

        Thread t5 = new Thread(
                () -> {
                    sharedResource5.consume(readWriteLock);
                }
        );

        SharedResource sharedResource6 = new SharedResource();

        Thread t6 = new Thread(
                () -> {
                    sharedResource6.consume(readWriteLock);
                }
        );

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t6.start();

        /*
         */
    }
}
