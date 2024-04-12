package org.concurrency.ThreadPoolExecutors.FixedThreadPool.Imp1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicInteger;

public class CustomThreadPoolExecutor implements CustomExecutorService{
    private volatile AtomicInteger totalWorkerThreadSoFar = new AtomicInteger(0);
    private volatile int poolSize;
    private BlockingQueue<Runnable> taskQueue;
    private List<WorkerThread> threads;

    public CustomThreadPoolExecutor(int fixedThreadPoolSize){
        this.poolSize = fixedThreadPoolSize;
        this.taskQueue = new LinkedBlockingDeque<>();
        this.threads = new ArrayList<WorkerThread>(fixedThreadPoolSize);
    }


    @Override
    public void shutdown() {
        for(WorkerThread thread : threads){
            thread.stopThread();
        }
    }

    @Override
    public void shutdownNow() {

    }

    @Override
    public void submit(Runnable task) {
        try {
            taskQueue.put(task); // put() is thread safe.
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        if(totalWorkerThreadSoFar.get() < poolSize) {
            totalWorkerThreadSoFar.incrementAndGet();

            WorkerThread workerThread = new WorkerThread(taskQueue);
            threads.add(workerThread);
            workerThread.start();
        }
    }

    @Override
    public void execute(Runnable command) {

    }
}
