package org.concurrency.Questions.DesignSemaphore.Imp2;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
We use a ReentrantLock to handle the mutual exclusion.
We use a Condition to handle the waiting and signaling of threads.
acquire(): Decrements the semaphore's permit count. If the permit count is zero, the calling thread will block until a permit is available.
release(): Increments the semaphore's permit count and wakes up a waiting thread if any.
availablePermits(): Returns the current number of permits available.

In this implementation, the acquire() method will block when the permit count is 0 or negative, and the release() method will increment the permit count and signal waiting threads, ensuring correct behavior.
 */
public class SemaphoreUsingLock {
    private final Lock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();
    private int permits;

    public SemaphoreUsingLock(int permits) {
        if (permits < 0) {
            throw new IllegalArgumentException("Number of permits cannot be negative");
        }
        this.permits = permits;
    }

    public void acquire() throws InterruptedException {
        lock.lock();
        try {
            while (permits <= 0) {
                condition.await();
            }
            permits--;
        } finally {
            lock.unlock();
        }
    }

    public void release() {
        lock.lock();
        try {
            permits++;
            condition.signal();
        } finally {
            lock.unlock();
        }
    }

    public int availablePermits() {
        lock.lock();
        try {
            return permits;
        } finally {
            lock.unlock();
        }
    }
}

