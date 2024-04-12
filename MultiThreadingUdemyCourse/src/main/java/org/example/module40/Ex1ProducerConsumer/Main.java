package org.example.module40.Ex1ProducerConsumer;

public class Main {
    public static void main(String[] args) {
        SharedResource sharedResource = new SharedResource(6);

        Thread producerThread = new Thread(
                () -> {
                    for(int i = 0; i <= 60; i++){
                        try {
                            sharedResource.produce(i);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
        );

        Thread consumerThread = new Thread(
                () -> {
                    for(int i = 0; i <= 60; i++){
                        try {
                            sharedResource.consume();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
        );

        producerThread.start();
        consumerThread.start();
        /*
        Buffer is Empty, Consumer is waiting for producer
        Produced: 0
        Produced: 1
        Produced: 2
        Buffer is full, Producer is waiting for consumer
        Consumed: 0
        Consumed: 1
        Consumed: 2
        Buffer is Empty, Consumer is waiting for producer
        Produced: 3
        Produced: 4
        Produced: 5
        Buffer is full, Producer is waiting for consumer
        Consumed: 3
        Consumed: 4
        Consumed: 5
        Buffer is Empty, Consumer is waiting for producer
        Produced: 6
        Consumed: 6
         */
    }
}
