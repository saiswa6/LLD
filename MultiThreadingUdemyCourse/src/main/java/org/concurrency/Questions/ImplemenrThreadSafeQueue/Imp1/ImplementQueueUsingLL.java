package org.concurrency.Questions.ImplemenrThreadSafeQueue.Imp1;

import java.util.NoSuchElementException;
import java.util.concurrent.locks.ReentrantLock;

/*
User
Implement a threadsafe queue
- The synchronization is achieved using ReentrantLock to ensure thread safety during enqueue and dequeue operations.

In this implementation:
- enqueue() method adds an item to the end of the queue.
- dequeue() method removes and returns the item at the front of the queue.
- isEmpty() method checks if the queue is empty.
- A ReentrantLock (lock) is used to synchronize access to the shared variables head and tail to ensure thread safety.
This thread-safe queue can be safely accessed by multiple threads without causing any race conditions or data inconsistencies.

============
- A Bounded Blocking Queue is a special type of thread-safe queue that has a maximum capacity. When the queue is full, attempts to enqueue new items will block until space becomes available, and when the queue is empty, attempts to dequeue items will block until items are available.
- The basic thread-safe queue implementation provided above is not a Bounded Blocking Queue because it does not have a maximum capacity, and it does not block when attempting to enqueue or dequeue items.
- If you want to implement a Bounded Blocking Queue, you can modify the above implementation to add the maximum capacity and use Condition variables to handle blocking operations. Here's a simplified example of a Bounded Blocking Queue:
 */

import java.util.NoSuchElementException;
import java.util.concurrent.locks.ReentrantLock;

public class ImplementQueueUsingLL<T> {

    private Node<T> head;
    private Node<T> tail;
    private final ReentrantLock lock = new ReentrantLock();

    private static class Node<T> {
        T data;
        Node<T> next;

        Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    /**
     * Add an item to the end of the queue.
     *
     * @param item the item to be added to the queue
     */
    public void enqueue(T item) {
        Node<T> newNode = new Node<>(item);

        lock.lock();
        try {
            if (tail == null) {
                head = newNode;
                tail = newNode;
            } else {
                tail.next = newNode;
                tail = newNode;
            }
        } finally {
            lock.unlock();
        }
    }

    /**
     * Remove and return the item at the front of the queue.
     *
     * @return the item at the front of the queue
     * @throws NoSuchElementException if the queue is empty
     */
    public T dequeue() {
        lock.lock();
        try {
            if (head == null) {
                throw new NoSuchElementException("Queue is empty");
            }

            T item = head.data;
            head = head.next;

            if (head == null) {
                tail = null;
            }

            return item;
        } finally {
            lock.unlock();
        }
    }

    /**
     * Check if the queue is empty.
     *
     * @return true if the queue is empty, false otherwise
     */
    public boolean isEmpty() {
        lock.lock();
        try {
            return head == null;
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        ImplementQueueUsingLL<Integer> queue = new ImplementQueueUsingLL<>();

        // Enqueue some items
        for (int i = 1; i <= 5; i++) {
            queue.enqueue(i);
        }

        // Dequeue and print items
        while (!queue.isEmpty()) {
            System.out.println(queue.dequeue());
        }

        // Try to dequeue from an empty queue
        try {
            queue.dequeue();
        } catch (NoSuchElementException e) {
            System.out.println("Queue is empty");
        }
    }
}

