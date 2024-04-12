package org.example.module40.Ex1ProducerConsumer;

import java.util.LinkedList;
import java.util.Queue;

public class SharedResource {
    private Queue<Integer> sharedBuffer;
    private int bufferSize;

    SharedResource(int bufferSize){
        sharedBuffer = new LinkedList<>();
        this.bufferSize = bufferSize;
    }

    public synchronized void produce(int item) throws InterruptedException {
        //If buffer is full, wait for the consumer to consume items
        while (sharedBuffer.size() == bufferSize){
            System.out.println("Buffer is full, Producer is waiting for consumer");
            wait();
        }

        sharedBuffer.add(item);
        System.out.println("Produced: " + item);
        //Notify the consumer that there are items to consume now
        notify();
    }

    public synchronized int consume() throws InterruptedException {
        //Thread.sleep(100);
        //Buffer is empty, wait for the producer to produce items
        while (sharedBuffer.isEmpty()){
            System.out.println("Buffer is Empty, Consumer is waiting for producer");
            wait();
        }

        int item = sharedBuffer.poll();
        System.out.println("Consumed: "+ item);
        //Notify the producer that there is space in the buffer now
        notify();
        return item;
    }
}
