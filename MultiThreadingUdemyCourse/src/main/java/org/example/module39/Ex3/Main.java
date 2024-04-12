package org.example.module39.Ex3;

public class Main {
    public static void main(String[] args) {
        SharedResource sharedResource = new SharedResource();

        // producer and consumer working on shared resource.
        Thread producerThread = new Thread(new ProduceTask(sharedResource));
        Thread consumerThread = new Thread(new ConsumeTask(sharedResource));

        // Thread is in in RUNNABLE state
        producerThread.start();
        consumerThread.start();

        // first consumer thread acquire monitor lock as producer is sleeping. consumer is waiting and
        // release monitor lock on sharedResource.
        // Now producer acquire lock after 4 secs.
        // notify consumer where it is waiting.

        System.out.println("Main method end");

        /*
        Main method end
        Consumer Thread: Thread-1
        Consume Item method invoked by Thread-1
        Producer Thread: Thread-0
        Thread Thread-1 is waiting now
        Item added by thread Thread-0 and invoking all threads which are waiting
        Item consumed by Thread-1
         */
    }
}
