package org.example.module39.practice;

public class Main {

    public static void main(String[] args) {  /// This is very much Important.
        BoundedBuffer boundedBuffer = new BoundedBuffer(10);

        Thread producerThread =  new Thread(
                () -> {
                    for(int i=0; i < 100; i++) {
                        boundedBuffer.produce(i);
                    }
                }
        );

        Thread consumerThread =  new Thread(
                () -> {
                    for(int i=0; i < 100; i++) {
                        boundedBuffer.consume();
                    }
                }
        );

        producerThread.start();
        consumerThread.start();

    }

}
