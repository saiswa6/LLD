package org.concurrency.Questions.ScheduleWaitUntilComplete.Imp2;


import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
/*
Using Blocking Queue -- solution below may not be correct
--------------------
1.schedule() will add the job to the end of BlockingDequeue. Set atomic boolean areAllComplete to false.
2.Consumers will keep polling the BlockingDequeue from the front.
3.Once execution yields result, store the result in a HashMap.
4.If the BlockingDequeue is empty, flip the atomic boolean areAllComplete to true.
5.waitUntilComplete goes into a non-blocking wait until areAllComplete is true. Then returns.
 */

public class BlockingQueue {
    private final BlockingDeque<Runnable> deq;
    private final Lock lock;
    private final Condition cv;
    private final Thread[] threads;
    private final AtomicBoolean areAllComplete;
    private final Map<Runnable, Object> results;

    public BlockingQueue() {
        this.deq = new LinkedBlockingDeque<>();
        this.lock = new ReentrantLock();
        this.cv = lock.newCondition();
        this.threads = new Thread[8];  // 8 worker threads
        this.areAllComplete = new AtomicBoolean(false);
        this.results = new HashMap<>();

        for (int i = 0; i < 8; i++) {
            threads[i] = new Thread(this::worker);
            threads[i].start();
        }
    }

    public void doJob(Runnable job) {
        // do something
        System.out.println("Doing job: " + job);
        results.put(job, "Result of " + job);
    }

    public void worker() {
        while (true) {
            lock.lock();
            try {
                Runnable job = deq.poll();
                if (job == null && areAllComplete.get()) {
                    return;
                }
                if (job != null) {
                    doJob(job);
                }
            } finally {
                lock.unlock();
            }
        }
    }

    public void schedule(Runnable job) {
        lock.lock();
        try {
            deq.offer(job);
            cv.signal();
            areAllComplete.set(false);
        } finally {
            lock.unlock();
        }
    }

    public void waitUntilComplete() {
        lock.lock();
        try {
            while (!areAllComplete.get()) {
                cv.await();
            }
            System.out.println(" Waiting for all to complete work");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        BlockingQueue solution = new BlockingQueue();

        // Schedule some jobs
        for (int i = 0; i < 10; i++) {
            final int j = i;
            solution.schedule(() -> {
                System.out.println("Executing job: " + j);
            });
        }

        // Wait until all jobs are completed
        solution.waitUntilComplete();
        System.out.println("All jobs completed. Results: " + solution.results);
    }
}

