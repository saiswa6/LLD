package org.example.module39.practice;

import java.util.ArrayDeque;
import java.util.Queue;

public class BoundedBuffer {
    Queue<Integer> queue ;
    int capacity;

    public BoundedBuffer(int capacity) {
        this.capacity = capacity;
        this.queue = new ArrayDeque<>(capacity);
    }

    public synchronized void produce(int item) {
        while(queue.size() > capacity){
            try{
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        queue.offer(item);
        System.out.println("Produced item " + item);
        notify();
    }

    public synchronized int consume() {
        while (queue.isEmpty()) {
            try{
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        int value = queue.poll();
        System.out.println("Consumed item " + value);
        notify();
        return value;

    }
}
