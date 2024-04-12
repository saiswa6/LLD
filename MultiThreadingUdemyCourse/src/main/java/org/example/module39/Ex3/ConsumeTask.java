package org.example.module39.Ex3;


public class ConsumeTask implements Runnable{

    SharedResource sharedResource;
    ConsumeTask(SharedResource sharedResource){
        this.sharedResource = sharedResource;
    }
    @Override
    public void run() {
        System.out.println("Consumer Thread: "+ Thread.currentThread().getName());

        sharedResource.consumeItem();
    }
}

