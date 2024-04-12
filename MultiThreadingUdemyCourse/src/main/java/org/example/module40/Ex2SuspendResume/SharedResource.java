package org.example.module40.Ex2SuspendResume;

public class SharedResource {
    boolean itemAvailable = false;

    public synchronized void produce(){
        itemAvailable = true;
        System.out.println("Lock acquired by "+ Thread.currentThread().getName());
        try {
            Thread.sleep(8000);
        } catch (InterruptedException e){
            throw  new RuntimeException(e);
        }
        System.out.println("Lock released by "+ Thread.currentThread().getName());
    }
}
