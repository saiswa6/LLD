package org.concurrency.Questions.DesignSemaphore.Imp1;

/*
acquire(): Decrements the semaphore's permit count. If the permit count is zero, the calling thread will block until a permit is available.
release(): Increments the semaphore's permit count and wakes up a waiting thread if any.
availablePermits(): Returns the current number of permits available.
 */

public class Semaphore {
    private int permits;

    public Semaphore(int permits) {
        if (permits < 0) {
            throw new IllegalArgumentException("Number of permits cannot be negative");
        }
        this.permits = permits;
    }

    public synchronized void acquire() throws InterruptedException {
        while (permits <= 0) {
            wait();
        }
        permits--;
    }

    public synchronized void release() {
        permits++;
        notify();
    }

    public synchronized int availablePermits() {
        return permits;
    }
}

