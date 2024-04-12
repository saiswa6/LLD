package org.concurrency.Questions.LRUCache.Imp3WithReentrantReadWriteLock;

import java.util.HashMap;
import java.util.Map;


import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;
/*
One more ssolution from comment
Yes both functions need to be thread-safe, but you cant directly lock both. Otherwise performance will be very bad

internally you have a map
First you need a mutex for the map

Next you would have a double linklist to keep track of head and tail
Every element of the list needs to have its own mutex
When removing element from double;e linklist
take a lock on previous, current , next
Take in this order only otherwise you can get into deadlock
 */

class ListNode{
    int key;
    int value;
    ListNode prev;
    ListNode next;

    public ListNode(int key, int value) {
        this.key = key;
        this.value = value;
    }
}

class LRUWithReentrantReadWriteLock {
    int capacity;
    Map<Integer, ListNode> map;
    ListNode head;
    ListNode tail;

    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public LRUWithReentrantReadWriteLock(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>();
        head = new ListNode(-1,-1);
        tail = new ListNode(-1,-1);
        head.next = tail;
        tail.prev = head;

    }
    /*

ReentrantReadWriteLock is used to lock write operations (put, remove, clear) and read operations (get, containsKey, size), allowing multiple concurrent read operations but ensuring exclusive access for write operations.
The lock object is used to lock critical sections of the code to ensure thread safety while allowing concurrent read operations for improved performance.






     */

    public synchronized int get(int key) {  // Add synchronized
        lock.readLock().lock();
        try{
            if(!map.containsKey(key)) {
                return -1;
            }

            ListNode node = map.get(key);
            remove(node);
            add(node);
            return node.value;
        } finally {
            lock.readLock().unlock();
        }

    }

    public synchronized void put(int key, int value) { // Add synchronized
        lock.writeLock().lock();
        try{
            if(map.containsKey(key)){
                ListNode oldListNode = map.get(key);
                remove(oldListNode);
            }

            ListNode node = new ListNode(key, value);
            map.put(key, node);
            add(node);

            if(map.size() > capacity) {
                ListNode ListNodeToBeDeleted = head.next;
                remove(ListNodeToBeDeleted);
                map.remove(ListNodeToBeDeleted.key);
            }
        } finally {
            lock.writeLock().unlock();
        }

    }

    public void add(ListNode node) {
        ListNode previousEnd = tail.prev;
        previousEnd.next = node;
        node.prev = previousEnd;
        node.next = tail;
        tail.prev = node;
    }

    public void remove(ListNode node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }
}

