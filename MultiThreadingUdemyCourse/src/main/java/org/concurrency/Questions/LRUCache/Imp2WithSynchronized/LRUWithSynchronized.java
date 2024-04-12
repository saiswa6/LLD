package org.concurrency.Questions.LRUCache.Imp2WithSynchronized;

import java.util.HashMap;
import java.util.Map;

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

class LRUWithSynchronized {
    int capacity;
    Map<Integer, ListNode> map;
    ListNode head;
    ListNode tail;

    public LRUWithSynchronized(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>();
        head = new ListNode(-1,-1);
        tail = new ListNode(-1,-1);
        head.next = tail;
        tail.prev = head;

    }
    /*
    All public methods (get, put, remove, containsKey, size, clear) are synchronized to ensure thread safety.
    After this, suggest Reentrant Lock.
     */

    public synchronized int get(int key) {  // Add synchronized
        if(!map.containsKey(key)) {
            return -1;
        }

        ListNode node = map.get(key);
        remove(node);
        add(node);
        return node.value;
    }

    public synchronized void put(int key, int value) { // Add synchronized
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
