package org.example.module42.Ex4StampedLock1;

import java.util.concurrent.locks.StampedLock;

public class SharedResource {
    boolean isAvailable = false;
    StampedLock stampedLock = new StampedLock();


    public void producer(){
        long stamp =stampedLock.readLock();
        try{
            System.out.println("Read Lock acquired by : " + Thread.currentThread().getName());
            isAvailable = true;
            Thread.sleep(6000);
        } catch (Exception e){

        }finally {
            System.out.println("Read Lock released by :  "+ Thread.currentThread().getName());
            stampedLock.unlockRead(stamp);
        }
    }

    public void consume(){
        long stamp = stampedLock.writeLock();
        try{
            System.out.println("Write Lock acquired by : " + Thread.currentThread().getName());
            isAvailable = false;
        } catch (Exception e){

        } finally {
            System.out.println("Write Lock released by :  "+ Thread.currentThread().getName());
            stampedLock.unlockWrite(stamp);
        }
    }
}
