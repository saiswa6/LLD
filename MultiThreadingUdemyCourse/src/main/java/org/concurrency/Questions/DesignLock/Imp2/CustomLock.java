package org.concurrency.Questions.DesignLock.Imp2;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Predicate;

/*
Implemented directly using ReentrantLock.
 */
public class CustomLock {
    private final Lock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();
    private boolean isLocked = false;

    public void lock() {
        lock.lock();
        try {
            while (isLocked) {
                condition.await();
            }
            isLocked = true;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            lock.unlock();
        }
    }

    public void unlock() {
        lock.lock();
        try {
            isLocked = false;
            condition.signal();
        } finally {
            lock.unlock();
        }
    }

    public void lockWhen(Predicate<Void> predicate) throws InterruptedException {
        lock();
        try {
            while (!predicate.test(null)) {
                condition.await();
            }
        } finally {
            unlock();
        }
    }

    public static void main(String[] args) {
        CustomLock customLock = new CustomLock();

        // Example usage
        try {
            customLock.lock();
            System.out.println("Lock acquired");

            // Do some work

        } finally {
            customLock.unlock();
            System.out.println("Lock released");
        }
    }
}

