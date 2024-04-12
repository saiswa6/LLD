package org.example.module39.practic2;

import java.util.ArrayDeque;
import java.util.Queue;

public class SharedResource {
    int capacity;
    Queue<Integer> boundedBuffer;
    public SharedResource(int capacity){
        this.capacity = capacity;
        this.boundedBuffer = new ArrayDeque<>(capacity);
    }

    public void addItem(int item) {
        boundedBuffer.offer(item);
    }
}
