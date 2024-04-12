package org.example.module42.Ex6SemaphoreLock;

import java.util.concurrent.Semaphore;

public class SharedResource {
    boolean isAvailable = false;
    Semaphore semaphoreLock = new Semaphore(2);

    public void producer(){
        try{
            semaphoreLock.acquire();
            System.out.println("Lock acquired by : " + Thread.currentThread().getName());
            isAvailable = true;
            Thread.sleep(4000);
        } catch (Exception e){

        }
        finally {
            System.out.println("Lock released by : " + Thread.currentThread().getName());
            semaphoreLock.release();
        }
    }
}
