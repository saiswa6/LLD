package org.concurrency.ThreadPoolExecutors.FixedThreadPool.Imp1;

import java.util.concurrent.BlockingQueue;

public class WorkerThread extends Thread{

    private BlockingQueue<Runnable> taskQueue;
    private volatile boolean isStopped = false;

    public WorkerThread(BlockingQueue<Runnable> queue) {
        this.taskQueue = queue;
    }

    @Override
    public void run() {
        while (!isStopped) {
            try{
                Thread.sleep(1000);
                Runnable runnable = taskQueue.take(); // take is thread safe. // Retrieves and removes the head of this queue, waiting if necessary until an element becomes available.
                runnable.run();
            } catch (InterruptedException e){
                //e.printStackTrace();
                // Re-interrupt the thread and handle interruption
                //Thread.currentThread().interrupt();

                throw new RuntimeException("Thread Interrupted");
            }
        }
        if(isStopped && taskQueue.size() > 0) {
            try{
                Thread.sleep(1000);
                Runnable runnable = taskQueue.take(); // take is thread safe. // Retrieves and removes the head of this queue, waiting if necessary until an element becomes available.
                runnable.run();
            } catch (InterruptedException e){
                //e.printStackTrace();
                // Re-interrupt the thread and handle interruption
                Thread.currentThread().interrupt();

                throw new RuntimeException("Thread Interrupted");
            }
        } else {
            Thread.currentThread().interrupt();
        }
    }

    public synchronized void stopThread(){
        isStopped = true;
        //this.interrupt();
    }

    public synchronized boolean isStopped(){
        return isStopped;
    }
}
