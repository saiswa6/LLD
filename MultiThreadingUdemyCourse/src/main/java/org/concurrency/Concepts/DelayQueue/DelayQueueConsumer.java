package org.concurrency.Concepts.DelayQueue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class DelayQueueConsumer implements Runnable{
    private BlockingQueue<DelayedObject> queue;
    private Integer numberOfElementsToTake;
    public AtomicInteger numberOfConsumedElements = new AtomicInteger();

  public DelayQueueConsumer(BlockingQueue<DelayedObject> queue,Integer numberOfElementsToTake ){
      this.numberOfElementsToTake = numberOfElementsToTake;
      this.queue = queue;
  }

    @Override
    public void run() {
        for (int i = 0; i < numberOfElementsToTake; i++) {
            try {
                DelayedObject object = queue.take();
                numberOfConsumedElements.incrementAndGet();
                System.out.println("Consumer take: " + object);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
