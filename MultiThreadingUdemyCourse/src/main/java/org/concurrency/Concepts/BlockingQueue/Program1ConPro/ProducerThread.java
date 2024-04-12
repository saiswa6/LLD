package org.concurrency.Concepts.BlockingQueue.Program1ConPro;

import java.util.concurrent.BlockingQueue;

public class ProducerThread implements Runnable{
    private BlockingQueue<Integer> queue;

    public ProducerThread(BlockingQueue<Integer> queue){
        this.queue = queue;
    }
    @Override
    public void run() {
        int counter = 0;
        while (true) {
            try {
                queue.put(counter);
                System.out.println("Putting item into the queue ... " + counter);
                counter++;
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
