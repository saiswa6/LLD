package org.concurrency.Concepts.BlockingQueue.Program1ConPro;

import java.util.concurrent.BlockingQueue;

public class ConsumerThread implements Runnable{
    private BlockingQueue<Integer> queue;

    public ConsumerThread(BlockingQueue<Integer> queue){
        this.queue = queue;
    }
    @Override
    public void run() {
        int counter = 0;
        while (true) {
            try {
                int counterVal = queue.take();
                System.out.println("Taking item from the queue ... " + counterVal);
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
