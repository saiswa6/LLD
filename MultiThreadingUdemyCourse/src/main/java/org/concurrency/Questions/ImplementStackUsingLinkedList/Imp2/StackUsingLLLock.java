package org.concurrency.Questions.ImplementStackUsingLinkedList.Imp2;

/*
if you want to further enhance the thread safety without using the synchronized keyword,
you can use the java.util.concurrent.locks.Lock interface to explicitly lock and unlock critical sections of the code where shared resources are accessed.

- We introduced a ReentrantLock named lock to lock and unlock critical sections of the code where shared resources (i.e., the top reference and the nodes of the stack) are accessed.
- Each method now locks the lock before accessing/modifying the shared resources and unlocks it afterward using the try-finally block to ensure the lock is released even if an exception occurs.

This explicit locking mechanism provides more control over the synchronization process and can sometimes offer better performance than the implicit synchronization provided by the synchronized keyword.

 */


import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class StackUsingLLLock<T> {

    private Node<T> top;
    private final Lock lock = new ReentrantLock();

    private static class Node<T> {
        final T item;
        Node<T> next;

        Node(T item) {
            this.item = item;
        }
    }

    /**
     * Pushes an element onto the stack.
     *
     * @param item the element to push
     */
    public void push(T item) {
        lock.lock();
        try {
            Node<T> newNode = new Node<>(item);
            newNode.next = top;
            top = newNode;
        } finally {
            lock.unlock();
        }
    }

    /**
     * Removes and returns the element at the top of the stack.
     *
     * @return the element at the top of the stack
     * @throws java.util.NoSuchElementException if the stack is empty
     */
    public T pop() {
        lock.lock();
        try {
            if (isEmpty()) {
                throw new java.util.NoSuchElementException("Stack is empty");
            }
            T item = top.item;
            top = top.next;
            return item;
        } finally {
            lock.unlock();
        }
    }

    /**
     * Returns the element at the top of the stack without removing it.
     *
     * @return the element at the top of the stack
     * @throws java.util.NoSuchElementException if the stack is empty
     */
    public T peek() {
        lock.lock();
        try {
            if (isEmpty()) {
                throw new java.util.NoSuchElementException("Stack is empty");
            }
            return top.item;
        } finally {
            lock.unlock();
        }
    }

    /**
     * Checks if the stack is empty.
     *
     * @return true if the stack is empty, false otherwise
     */
    public boolean isEmpty() {
        lock.lock();
        try {
            return top == null;
        } finally {
            lock.unlock();
        }
    }

    /**
     * Returns the size of the stack.
     *
     * @return the size of the stack
     */
    public int size() {
        lock.lock();
        try {
            int size = 0;
            Node<T> current = top;
            while (current != null) {
                size++;
                current = current.next;
            }
            return size;
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        StackUsingLLLock<Integer> stack = new StackUsingLLLock<>();

        // Push elements onto the stack
        stack.push(1);
        stack.push(2);
        stack.push(3);

        // Pop elements from the stack
        System.out.println(stack.pop());  // Output: 3
        System.out.println(stack.pop());  // Output: 2
        System.out.println(stack.pop());  // Output: 1
    }
}

