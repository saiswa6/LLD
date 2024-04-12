package org.example.module42.Ex5StampedLock2;

import java.util.concurrent.locks.StampedLock;

public class SharedResource {
    int a = 10;
    StampedLock stampedLock = new StampedLock();


    public void producer(){
        long stamp =stampedLock.tryOptimisticRead();
        try{
            System.out.println("Taken Optimistic Read");
            a = 11;
            Thread.sleep(6000);

            if(stampedLock.validate(stamp)){
                System.out.println("Updated a value successfully");
            } else {
                System.out.println("rollback of work");
                a = 10; // rollback
            }
        } catch (Exception e){

        }
    }



    public void consume(){
        long stamp = stampedLock.writeLock();

        try{
            System.out.println("Write Lock acquired by : " + Thread.currentThread().getName());
            a = 9;
        } catch (Exception e){

        } finally {
            System.out.println("Write Lock released by :  "+ Thread.currentThread().getName());
            stampedLock.unlockWrite(stamp);
        }
    }
}
