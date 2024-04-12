package org.concurrency.Concepts.CountDownLatch.Program1;

import java.util.concurrent.CountDownLatch;

public class Latch implements Runnable{

    private int id;
    private CountDownLatch latch;

    public Latch(int id, CountDownLatch latch) {
        this.id = id;
        this.latch = latch;
    }

    @Override
    public void run() {
        doWork();
        latch.countDown();

    }

    private void doWork() {
        try{
            System.out.println("Thread  with ID " + this.id + "starts working...");
            Thread.sleep(3000);
        } catch (Exception e) {
            System.out.println("Exception ");
            e.printStackTrace();
        }
    }
}
