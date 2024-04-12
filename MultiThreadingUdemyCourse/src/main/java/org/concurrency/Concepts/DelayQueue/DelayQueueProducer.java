package org.concurrency.Concepts.DelayQueue;

import java.util.UUID;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Delayed;

public class DelayQueueProducer implements Runnable{
    private BlockingQueue<DelayedObject> queue;
    private Integer numberOfEleemntsToProduce;
    private Integer dalayOfEachProducedMessageMilliSeconds;
    DelayQueueProducer( BlockingQueue<DelayedObject> queue,Integer numberOfEleemntsToProduce,Integer dalayOfEachProducedMessageMilliSeconds){
        this.queue = queue;
        this.numberOfEleemntsToProduce = numberOfEleemntsToProduce;
        this.dalayOfEachProducedMessageMilliSeconds = dalayOfEachProducedMessageMilliSeconds;
    }
    @Override
    public void run() {
        for(int i=0; i<numberOfEleemntsToProduce;i++) {
            DelayedObject object = new DelayedObject(UUID.randomUUID().toString(), dalayOfEachProducedMessageMilliSeconds);
            System.out.println("Put Object : " + object);

            try{
                queue.put(object);
                Thread.sleep(500);
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
        }

    }
}
