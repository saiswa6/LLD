package org.concurrency.Questions.DesignLock.Imp1;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.function.Predicate;

/*
let's implement a basic Lock from scratch using Java's synchronized keyword.

The lockWhen(Predicate<Void> predicate) and signalAll() methods are added to demonstrate additional functionality similar to the ReentrantLock.


- A Predicate is a functional interface in Java that represents a boolean-valued function of one argument. It is often used as a lambda expression to filter or test objects.
- In the context of the CustomLock class, the lockWhen(Predicate<Void> predicate) method takes a Predicate<Void> as an argument. This predicate is used to determine whether a lock should be acquired.

Here's a brief explanation:
1. Predicate: A functional interface that takes one argument and returns a boolean.
2. Predicate<Void>: A predicate that takes a Void argument and returns a boolean.
In the CustomLock class, lockWhen method waits until the given predicate returns true.
 */
public class CustomLock {

    private boolean isLocked = false;
    private Thread lockedBy = null;
    private int lockedCount = 0;

    public synchronized void lock() throws InterruptedException {
        Thread callingThread = Thread.currentThread();
        while (isLocked && lockedBy != callingThread) {
            wait();
        }
        isLocked = true;
        lockedCount++;
        lockedBy = callingThread;
    }

    public synchronized void unlock() {
        if (Thread.currentThread() == this.lockedBy) {
            lockedCount--;

            if (lockedCount == 0) {
                isLocked = false;
                notify();
            }
        }
    }

    public synchronized void lockInterruptibly() throws InterruptedException {
        if (Thread.interrupted()) {
            throw new InterruptedException();
        }
        lock();
    }

    public synchronized boolean tryLock() {
        if (!isLocked) {
            isLocked = true;
            lockedCount++;
            lockedBy = Thread.currentThread();
            return true;
        }
        return false;
    }

    public synchronized boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        long endTime = System.currentTimeMillis() + unit.toMillis(time);
        long remainingTime = time;

        while (!tryLock()) {
            wait(remainingTime);

            remainingTime = endTime - System.currentTimeMillis();
            if (remainingTime <= 0) {
                return false;
            }
        }
        return true;
    }

    public synchronized Condition newCondition() {
        return null;
        //return new ConditionObject();
    }

    public synchronized void lockWhen(Predicate<Void> predicate) throws InterruptedException {
        lock();
        try {
            while (!predicate.test(null)) {
                wait();
            }
        } finally {
            unlock();
        }
    }

    public synchronized void signalAll() {
        notifyAll();
    }
}

