package org.concurrency.ThreadPoolExecutors.ScheduledThreadPool;

import org.concurrency.ThreadPoolExecutors.FixedThreadPool.Imp1.WorkerThread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class CustomScheduledThreadPoolExecutor implements CustomScheduledExecutorService{

    private volatile AtomicInteger totalWorkerThreadSoFar = new AtomicInteger(0);
    private int poolSize ;
    //private PriorityBlockingQueue<ScheduledTask> taskQueue;
    private List<WorkerThread> threadList;

    public CustomScheduledThreadPoolExecutor(int fixedThrealPoolSize) {
        this.poolSize = fixedThrealPoolSize;
        this.threadList = new ArrayList<>(fixedThrealPoolSize);
        //this.taskQueue = new PriorityBlockingQueue<ScheduledTask>();
    }
    @Override
    public void submit(Runnable r, int delay, TimeUnit timeUnit) {
        long currentTime = System.currentTimeMillis();
    }

    @Override
    public void scheduleAtFixedRate(Runnable r, int initialDelay, int subsequentDay, TimeUnit timeUnit) {

    }

    @Override
    public void scheduleAtFixedDelay(Runnable r, int initialDelay, int subsequentDelay, TimeUnit timeUnit) {

    }

    @Override
    public void shutDown() {

    }
}
