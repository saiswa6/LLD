package org.example.BoundedBuffer;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class BoundedBuffer {
    private ReentrantLock lock;
    private Condition isFullCondition;
    private Condition isEmptyCondition;
    private Queue<Integer> buffer;
    private int capacity;

    BoundedBuffer(){
        lock = new ReentrantLock();
        isEmptyCondition = lock.newCondition();
        isFullCondition = lock.newCondition();
        buffer = new LinkedList<>();
        capacity = 10;
    }

    public void produce(int value){
        lock.lock();

        try {
            while (buffer.size() == capacity){
                isFullCondition.await();
            }
            buffer.add(value);
            System.out.println("Produced : " + value + " by " + Thread.currentThread().getName());
            isEmptyCondition.await();
        } catch (InterruptedException e){
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }

    public int consume(){
        lock.lock();
        try{
            while (buffer.isEmpty()){
                isEmptyCondition.signal();
            }
            int value = buffer.poll();
            System.out.println("Consumed : " + value + " by " + Thread.currentThread().getName());
            isFullCondition.signal();
            return value;
        }  finally {
            lock.unlock();
        }
    }
}
