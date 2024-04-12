package org.example.module42.Ex3ReadWriteLock;

import java.util.concurrent.locks.ReadWriteLock;

public class SharedResource {

    boolean isAvailable = false;

    public void producer(ReadWriteLock readWriteLock){
        try{
            readWriteLock.readLock().lock();
            System.out.println("Read Lock acquired by " + Thread.currentThread().getName());
            //statements to read the data
            Thread.sleep(6000);
        } catch (InterruptedException e){
            throw new RuntimeException(e);
        }
        finally {
            System.out.println("Read Lock released by " + Thread.currentThread().getName());
            readWriteLock.readLock().unlock();

        }
    }

    public void consume(ReadWriteLock readWriteLock){
        try{
            readWriteLock.writeLock().lock();
            //statements to write the data
            System.out.println("Write Lock acquired by "+ Thread.currentThread().getName());
            isAvailable = true;
        } catch (Exception e){

        }
        finally{
            System.out.println("Write Lock released by "+ Thread.currentThread().getName());
            readWriteLock.writeLock().unlock();

        }
    }
}
