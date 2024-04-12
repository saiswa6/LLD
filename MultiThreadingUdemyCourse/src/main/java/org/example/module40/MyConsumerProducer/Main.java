package org.example.module40.MyConsumerProducer;

public class Main {
    public static void main(String[] args) {
        SharedResource sharedResource = new SharedResource();
        Thread producerThread  = new Thread(
                () -> {
                    System.out.println("Inside producer Thread : " + Thread.currentThread().getName());
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e){
                        throw new RuntimeException(e);
                    }

                    sharedResource.addItem();
                }
        );

        Thread consumerThread  = new Thread(
                () -> {
                    System.out.println("Inside consumer Thread : " + Thread.currentThread().getName());
                    sharedResource.consumeItem();
                }
        );

        producerThread.start();;
        consumerThread.start();
    }
}
