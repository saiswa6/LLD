package org.concurrency.Questions.LRUCache.Imp1;

import java.util.HashMap;
import java.util.Map;

/*
Yes both functions need to be thread-safe, but you cant directly lock both. Otherwise performance will be very bad

internally you have a map
First you need a mutex for the map

Next you would have a double linklist to keep track of head and tail
Every element of the list needs to have its own mutex
When removing element from double;e linklist
take a lock on previous, current , next
Take in this order only otherwise you can get into deadlock
 */

class NormalLRUCache {
    int capacity;
    Map<Integer, ListNode> map;
    ListNode head;
    ListNode tail;

    public NormalLRUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>();
        head = new ListNode(-1,-1);
        tail = new ListNode(-1,-1);
        head.next = tail;
        tail.prev = head;

    }

    public int get(int key) {
        if(!map.containsKey(key)) {
            return -1;
        }

        ListNode node = map.get(key);
        remove(node);
        add(node);
        return node.value;
    }

    public void put(int key, int value) {
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

/*
2nd solution
Builtin :

In Java, we will be using LinkedHashMap, which is a hash map that maintains insertion order. It essentially implements the linked list for us in the same data structure as the hash map, with the add and remove methods built into the hash map operations.

class LRUCache {
    int capacity;
    LinkedHashMap<Integer, Integer> dic;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        dic = new LinkedHashMap<>(5, 0.75f, true) { // access order is true
            @Override
            protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
                return size() > capacity;
            }
        };
    }

    public int get(int key) {
        return dic.getOrDefault(key, -1);
    }

    public void put(int key, int value) {
        dic.put(key, value);
    }

}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */

