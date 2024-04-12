package org.example.module42.Ex1ReentrantLock;

import java.util.concurrent.locks.ReentrantLock;

public class SharedResource {
    boolean isAvailable = false;
    ReentrantLock lock = new ReentrantLock();

    public void producer(){
        try{
            lock.lock();
            System.out.println("Lock acquired by : "+ Thread.currentThread().getName());
            isAvailable = true;
            Thread.sleep(4000);
        } catch (InterruptedException e){
            throw new RuntimeException(e);
        }

        finally { // If Some exception, then also, lock releases for thread
            System.out.println("Lock release by : " + Thread.currentThread().getName());
            lock.unlock();
        }
    }

}
