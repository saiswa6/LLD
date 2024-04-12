package org.example.module42.Ex7Condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class SharedResource {
    boolean isAvailable = false;
    ReentrantLock lock = new ReentrantLock();
    Condition condition = lock.newCondition();

    public void producer(){
        try{
            lock.lock();
            System.out.println("Producer Lock acquired by "+ Thread.currentThread().getName());

            if(isAvailable){
                //already available., thread has to wait for it to consume
                System.out.println("Producer thread is waiting "+ Thread.currentThread().getName());
                condition.await();
            }
            isAvailable = true;
            condition.signal();
        } catch (Exception e){

        }finally {
            System.out.println("Producer Lock released by "+ Thread.currentThread().getName());
            lock.unlock();
        }
    }

    public void consume(){
        try{
            Thread.sleep(1000);
            lock.lock();
            System.out.println("Consumer Lock acquired by "+ Thread.currentThread().getName());

            if(!isAvailable){
                //already not available., thread has to wait for it to produced
                System.out.println("Consumer thread is waiting "+ Thread.currentThread().getName());
                condition.await();
            }
            isAvailable = false;
            condition.signal();
        } catch (Exception e){

        }finally {
            System.out.println("Consumer Lock released by "+ Thread.currentThread().getName());
            lock.unlock();
        }
    }
}
