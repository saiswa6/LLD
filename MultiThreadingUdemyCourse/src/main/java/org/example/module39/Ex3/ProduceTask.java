package org.example.module39.Ex3;

public class ProduceTask implements Runnable{

    SharedResource sharedResource;
    ProduceTask(SharedResource sharedResource){
        this.sharedResource = sharedResource;
    }
    @Override
    public void run() {
        System.out.println("Producer Thread: "+ Thread.currentThread().getName());

        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        sharedResource.addItem();
    }
}
