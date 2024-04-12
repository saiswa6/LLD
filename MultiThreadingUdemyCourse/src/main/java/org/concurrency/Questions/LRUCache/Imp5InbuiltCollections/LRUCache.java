package org.concurrency.Questions.LRUCache.Imp5InbuiltCollections;



import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;
/*
To achieve better efficiency and thread safety without using synchronized or ReentrantReadWriteLock, we can use java.util.concurrent.ConcurrentLinkedDeque for the doubly linked list and java.util.concurrent.ConcurrentHashMap for the hashmap. This approach avoids explicit locks and leverages the thread-safe data structures provided by Java's concurrent package.

ConcurrentHashMap is used for thread-safe put and get operations.
ConcurrentLinkedDeque is used to maintain the order of nodes in the doubly linked list. This deque provides thread-safe operations for adding and removing nodes from both ends of the list.
The get, put, remove, containsKey, and size methods do not require synchronization or explicit locks, making the operations more efficient.
The addNodeToFront, moveNodeToFront, removeNodeFromEnd, and removeNode methods are used to manipulate the deque and maintain the LRU order of nodes.
This implementation leverages Java's concurrent data structures to achieve thread safety and efficiency without the need for explicit synchronization or locks.
 */

public class LRUCache<K, V> {

    private final int capacity;
    private final Map<K, Node<K, V>> cacheMap;
    private final ConcurrentLinkedDeque<Node<K, V>> deque;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.cacheMap = new ConcurrentHashMap<>(capacity);
        this.deque = new ConcurrentLinkedDeque<>();
    }

    public V get(K key) {
        Node<K, V> node = cacheMap.get(key);
        if (node == null) {
            return null;
        }
        moveNodeToFront(node);
        return node.value;
    }

    public void put(K key, V value) {
        Node<K, V> node = cacheMap.get(key);
        if (node == null) {
            node = new Node<>(key, value);
            cacheMap.put(key, node);
            addNodeToFront(node);
            if (cacheMap.size() > capacity) {
                removeNodeFromEnd();
            }
        } else {
            node.value = value;
            moveNodeToFront(node);
        }
    }

    public void remove(K key) {
        Node<K, V> node = cacheMap.remove(key);
        if (node != null) {
            removeNode(node);
        }
    }

    public boolean containsKey(K key) {
        return cacheMap.containsKey(key);
    }

    public int size() {
        return cacheMap.size();
    }

    public void clear() {
        cacheMap.clear();
        deque.clear();
    }

    private void addNodeToFront(Node<K, V> node) {
        deque.addFirst(node);
    }

    private void moveNodeToFront(Node<K, V> node) {
        deque.remove(node);
        deque.addFirst(node);
    }

    private void removeNodeFromEnd() {
        Node<K, V> node = deque.removeLast();
        cacheMap.remove(node.key);
    }

    private void removeNode(Node<K, V> node) {
        deque.remove(node);
    }

    private static class Node<K, V> {
        K key;
        V value;

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

