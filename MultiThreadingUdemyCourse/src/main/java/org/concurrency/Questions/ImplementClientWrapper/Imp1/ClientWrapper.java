package org.concurrency.Questions.ImplementClientWrapper.Imp1;

import java.net.Inet4Address;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ClientWrapper {


    AtomicBoolean isInitDone = new AtomicBoolean(false);
    ReentrantLock initLock = new ReentrantLock();
    Condition initWait = initLock.newCondition();
    Semaphore requestLock = new Semaphore(0);
    AtomicInteger closePermits = new AtomicInteger(0);
    ReentrantLock closeLock = new ReentrantLock();
    AtomicBoolean isCloseDone = new AtomicBoolean(false);


    //Semaphore requestLock = new Semaphore(0);
    public void init(){
        while (!isInitDone.get()) {
            try {
                initLock.lock();
                Thread.sleep(10000);
                isInitDone.set(true);
                initWait.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();

            } finally {
                initLock.unlock();
            }
        }
    }

    public void request() {
        while (!isCloseDone.get()) {
            while (!isInitDone.get()) {
                try {
                    initWait.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if(closePermits.get() == 0){
                closePermits.set(1);
                requestLock.release();
            }


        }
    }

    public void close() throws InterruptedException {
        if(!isCloseDone.get() && requestLock.availablePermits() == 0) {
//            while (!isInitDone.get()) {
//                try {
//                    initWait.await();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
                requestLock.acquire();
            try {
                Thread.sleep(5000);
                isCloseDone.set(true);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Requests are already closed");
        }
    }
}
