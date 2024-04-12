package org.concurrency.Questions.LRUCache.Imp4SameAsImp3;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;


/*
Design a data structure with constant time complexity, not restrictions on the space complexity, It should have the following methods
insert()
remove()
getRandom()
exists()
getLRU()/*


 */


/*
ConcurrentHashMap is used instead of a synchronized hashmap to provide thread-safe put and get operations.
ReentrantReadWriteLock is used to lock write operations (put, remove, clear) and read operations (get, containsKey, size), allowing multiple concurrent read operations but ensuring exclusive access for write operations.
The lock object is used to lock critical sections of the code to ensure thread safety while allowing concurrent read operations for improved performance.

 */

public class LRUCache<K, V> {

    private final int capacity;
    private final Map<K, Node<K, V>> cacheMap;
    private final Node<K, V> head;
    private final Node<K, V> tail;
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.cacheMap = new ConcurrentHashMap<>(capacity);
        this.head = new Node<>(null, null);
        this.tail = new Node<>(null, null);
        head.next = tail;
        tail.prev = head;
    }

    public V get(K key) {
        lock.readLock().lock();
        try {
            Node<K, V> node = cacheMap.get(key);
            if (node == null) {
                return null;
            }
            moveToHead(node);
            return node.value;
        } finally {
            lock.readLock().unlock();
        }
    }

    public void put(K key, V value) {
        lock.writeLock().lock();
        try {
            Node<K, V> node = cacheMap.get(key);
            if (node == null) {
                node = new Node<>(key, value);
                cacheMap.put(key, node);
                addNode(node);
                if (cacheMap.size() > capacity) {
                    removeTail();
                }
            } else {
                node.value = value;
                moveToHead(node);
            }
        } finally {
            lock.writeLock().unlock();
        }
    }

    public void remove(K key) {
        lock.writeLock().lock();
        try {
            Node<K, V> node = cacheMap.remove(key);
            if (node != null) {
                removeNode(node);
            }
        } finally {
            lock.writeLock().unlock();
        }
    }

    public boolean containsKey(K key) {
        lock.readLock().lock();
        try {
            return cacheMap.containsKey(key);
        } finally {
            lock.readLock().unlock();
        }
    }

    public int size() {
        lock.readLock().lock();
        try {
            return cacheMap.size();
        } finally {
            lock.readLock().unlock();
        }
    }

    public void clear() {
        lock.writeLock().lock();
        try {
            cacheMap.clear();
            head.next = tail;
            tail.prev = head;
        } finally {
            lock.writeLock().unlock();
        }
    }

    private void addNode(Node<K, V> node) {
        node.prev = head;
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
    }

    private void removeNode(Node<K, V> node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void moveToHead(Node<K, V> node) {
        removeNode(node);
        addNode(node);
    }

    private void removeTail() {
        Node<K, V> tailPrev = tail.prev;
        removeNode(tailPrev);
        cacheMap.remove(tailPrev.key);
    }

    private static class Node<K, V> {
        K key;
        V value;
        Node<K, V> prev;
        Node<K, V> next;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    public static void main(String[] args) {
        LRUCache<Integer, String> cache = new LRUCache<>(2);

        cache.put(1, "One");
        cache.put(2, "Two");

        System.out.println(cache.get(1)); // Output: One
        System.out.println(cache.get(2)); // Output: Two

        cache.put(3, "Three"); // Entry for key 1 will be evicted as it's the least recently used

        System.out.println(cache.containsKey(1)); // Output: false
        System.out.println(cache.get(3)); // Output: Three
    }
}

