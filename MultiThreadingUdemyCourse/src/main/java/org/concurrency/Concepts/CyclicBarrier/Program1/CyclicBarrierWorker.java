package org.concurrency.Concepts.CyclicBarrier.Program1;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierWorker implements Runnable{
    private int id;
    private Random random;
    CyclicBarrier barrier;

    public CyclicBarrierWorker(int id, CyclicBarrier barrier){
        this.id = id;
        this.random = new Random();
        this.barrier = barrier;
    }
    @Override
    public void run() {
        doWork();
    }

    private void doWork() {
        System.out.println("Thread with ID " + this.id + " starts the work ...");
        try{
            Thread.sleep(random.nextInt(3000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try{
            // when all threads call await() this is when
            // the barrier is broken.
            barrier.await();
        } catch (BrokenBarrierException | InterruptedException e){
            e.printStackTrace();
        }
        System.out.println("After the await() .....");
    }
}
