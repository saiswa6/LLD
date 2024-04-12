package org.concurrency.Concepts.DelayQueue;

import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(2);

        BlockingQueue<DelayedObject> queue = new DelayQueue<>();
        int numberOfElementsToProduce = 2;
        int delayOfEachProducedMessageMilliseconds = 500;
        DelayQueueConsumer consumer = new DelayQueueConsumer(
                queue, numberOfElementsToProduce);
        DelayQueueProducer producer = new DelayQueueProducer(
                queue, numberOfElementsToProduce, delayOfEachProducedMessageMilliseconds);

        // when
        executor.submit(producer);
        executor.submit(consumer);

        // then
        try{
            executor.awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            System.out.println("Exception occured");
            e.printStackTrace();
        }
        executor.shutdown();
        /*
        Put Object : org.concurrency.Concepts.DelayQueue.DelayedObject@3f2b458d
        Put Object : org.concurrency.Concepts.DelayQueue.DelayedObject@7e3f7658
        Consumer take: org.concurrency.Concepts.DelayQueue.DelayedObject@3f2b458d
        Consumer take: org.concurrency.Concepts.DelayQueue.DelayedObject@7e3f7658
         */

        //assertEquals(consumer.numberOfConsumedElements.get(), numberOfElementsToProduce);
    }
}
