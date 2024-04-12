package org.concurrency.Questions.DesignMutex.Imp1;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*

In Java, a mutex (short for "mutual exclusion") is often implemented using a ReentrantLock from the java.util.concurrent.locks package. A ReentrantLock is a synchronization primitive that works similarly to a synchronized block but offers more flexibility and control.
The criticalSection() method represents a critical section of code that you want to be accessed by only one thread at a time.
The ReentrantLock is used to acquire and release the lock around the critical section.

in the context of synchronization in Java, a ReentrantLock serves the same purpose as a mutex. Both are used to achieve mutual exclusion, ensuring that only one thread can execute a critical section of code at a time.

Here are a few distinctions and characteristics of ReentrantLock:
1. Reentrancy: ReentrantLock is reentrant, meaning a thread can acquire the lock multiple times without blocking itself, as long as it releases the lock the same number of times.
2. Condition Support: ReentrantLock provides additional features like support for condition variables, which can be used to wait for a certain condition to be met.
3. Interruptible Locking: The lock acquisition methods can be interrupted by other threads.
4. Fairness: ReentrantLock can be configured to provide a fair lock, ensuring that the longest-waiting thread gets the lock when it becomes available.
 */
public class MutexExample {
    private final Lock lock = new ReentrantLock();

    public void criticalSection() {
        lock.lock(); // Acquire the lock
        try {
            // Critical section
            System.out.println(Thread.currentThread().getName() + " is in critical section.");
            Thread.sleep(1000); // Simulate some work
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock(); // Release the lock
        }
    }

    public static void main(String[] args) {
        MutexExample example = new MutexExample();

        // Create multiple threads to access the critical section
        for (int i = 0; i < 5; i++) {
            Thread thread = new Thread(() -> example.criticalSection());
            thread.start();
        }
    }
}

