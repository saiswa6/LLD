package org.concurrency.Questions.ScheduleWaitUntilComplete.Imp1;


import java.util.Queue;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
    1.	Design a class, which contains two methods, schedule() and waitUntilComplete(), when user wanna excecute a work, will call schedule() (schedule() method will returns immediately), The function of waitUntilComplete is to wait for all the existing work to be executed, and then return. Because is muti-thread, need to consider thread safe.(https://leetcode.com/discuss/interview-question/911585/Rubrik-or-Phone)

    Repeated :
    Round: 1st interview round
    Implement two methods in a class, schedule() and waitUntilComplete().
    schedule() should enqueue work to be performed and should be non-blocking.
    waitUntilComplete() should block the call untill all scheduled work is completed.

    Builtin thread safe constructs like Deque, BlockingQueue, etc. can't be used. Implement it using locks, etc ensuring thread safety. (https://leetcode.com/discuss/interview-question/1938910/Rubrik-or-Phone-or-Task-scheduler)


29.  I have a single Threaded client which calls the following interface:

    class WorkerInterface{
    public void submitWork();// Accepts a Task and returns immediately
    public void blockUntilComplete(); //Upon Invocation the thread should be blocked until all submitted tasks are finished
    }
    I was allowed to modify the signatures to add necesssary params. (https://leetcode.com/discuss/interview-experience/1806959/Rubrik-or-SDE2-or-Bangalore-or-Feb-2022-or-Offer)

 */

public class Solution2 {

    private final Queue<Runnable> queue;
    private final int numWorkers = 8;
    private final Condition condition;
    private final Lock lock;
    private final Thread[] threads;

    public Solution2() {
        this.queue = new LinkedList<>();
        this.lock = new ReentrantLock();
        this.condition = lock.newCondition();
        this.threads = new Thread[numWorkers];

        for (int i = 0; i < numWorkers; i++) {
            threads[i] = new Thread(this::worker);
            /*
            threads[i] = new Thread(new Runnable() {
            @Override
            public void run() {
                worker();
            }
            });
             */
            threads[i].start();
        }
    }

    public void doJob(Runnable job) {
        job.run();
    }

    private void worker() {
        while (true) {
            lock.lock();
            try {
                while (queue.isEmpty()) {
                    condition.await(10, TimeUnit.SECONDS);
                }
                Runnable job = queue.poll();
                if (job == null) {
                    return;
                }
                doJob(job);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

    public void schedule(Runnable job) {
        lock.lock();
        try {
            queue.offer(job);
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public void waitUntilComplete() throws InterruptedException {
        lock.lock();
        try {
            while (!queue.isEmpty()) {
                condition.await();
            }
            for (Thread thread : threads) {
                queue.offer(() -> {});
                condition.signal();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        System.out.println("Waiting until all are completed");
    }

    public static void main(String[] args) throws InterruptedException {
        Solution2 solution = new Solution2();

        // Schedule some tasks
        for (int i = 0; i < 10; i++) {
            int taskNum = i;
            solution.schedule(() -> {
                System.out.println("Executing task " + taskNum);
                try {
                    Thread.sleep(1000); // Simulate some work
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        // Wait for all tasks to complete
        solution.waitUntilComplete();
        System.out.println("All tasks completed");
    }
    /*
    Executing task 0
    Executing task 1
    Executing task 2
    Executing task 3
    Executing task 4
    Executing task 5
    Executing task 6
    Executing task 7
    Executing task 8
    Executing task 9
    Waiting until all are completed
    All tasks completed
 */
}

