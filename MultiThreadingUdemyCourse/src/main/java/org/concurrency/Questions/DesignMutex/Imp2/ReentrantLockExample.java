package org.concurrency.Questions.DesignMutex.Imp2;



import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockExample {
    private final Lock lock = new ReentrantLock();

    public void outer() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " entered outer method.");
            inner();
        } finally {
            lock.unlock();
        }
    }

    public void inner() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " entered inner method.");
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        ReentrantLockExample example = new ReentrantLockExample();

        Thread t1 = new Thread(() -> example.outer());
        t1.start();

        Thread t2 = new Thread(() -> example.outer());
        t2.start();
    }
}

