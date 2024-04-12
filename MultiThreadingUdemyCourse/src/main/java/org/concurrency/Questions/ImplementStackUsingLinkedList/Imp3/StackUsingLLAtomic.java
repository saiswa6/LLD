package org.concurrency.Questions.ImplementStackUsingLinkedList.Imp3;

import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicReference;

/*
we can further enhance the performance of the thread-safe stack by using a non-blocking algorithm.
 One such approach is to use the compare-and-swap (CAS) operation provided by java.util.concurrent.atomic.AtomicReference to atomically update the top reference without explicit locking.

 We use AtomicReference<Node<T>> named top to represent the top node of the stack.
- The push() method uses a do-while loop with compareAndSet() to atomically update the top reference.
- The pop() method also uses a do-while loop with compareAndSet() to atomically remove the top element from the stack.
- The peek() method simply retrieves the top element without removing it.
- The isEmpty() and size() methods do not require atomic operations as they only read the top reference without modifying it.
- This lock-free implementation leverages the atomic compareAndSet() operation to achieve thread safety without the need for explicit locking, thereby potentially improving performance in highly concurrent scenarios.

 =================================

 The lock-free implementation using AtomicReference and compareAndSet() is already quite efficient for many concurrent scenarios.
 However, if you're looking for further performance improvements, you might consider using a more advanced data structure like a Treiber stack
 or a Michael-Scott queue, which are designed for high concurrency.

 --The Treiber stack algorithm uses a singly linked list and an AtomicReference to the top node.
The push() method uses a loop to atomically update the top reference.
The pop() method uses a loop to atomically remove the top element from the stack.
The peek(), isEmpty(), and size() methods are similar to the previous implementation.

Michael-Scott Queue Implementation
Alternatively, you can also use the Michael-Scott non-blocking queue algorithm to implement a lock-free stack, which is more complex but can provide better performance in some scenarios.

Both the Treiber stack and the Michael-Scott stack are non-blocking and can provide better performance in highly concurrent scenarios compared to the lock-based approach. You can choose the one that best suits your specific requirements and performance
 */
public class StackUsingLLAtomic<T> {

    private static class Node<T> {
        T item;
        Node<T> next;

        public Node(T item){
            this.item = item;
        }
    }


    private final AtomicReference<Node<T>> top = new AtomicReference<>();

    public void push(T item) {
        Node<T> newNode = new Node<T>(item);
        Node<T> currentTop;

        do{
            currentTop = top.get();
            newNode.next = currentTop;
        } while (!top.compareAndSet(currentTop,newNode));


    }

    public T pop(){
        Node<T> currentTop;
        Node<T> newTop;

        do{
            currentTop = top.get();
            if(currentTop == null) {
                throw  new NoSuchElementException("Stack is Empty");
            }
            newTop = currentTop.next;
        }while (!top.compareAndSet(currentTop, newTop));

        return currentTop.item;
    }

    public T peek() {
        Node<T> currentTop = top.get();
        if (currentTop == null) {
            throw new java.util.NoSuchElementException("Stack is empty");
        }
        return currentTop.item;
    }

    public boolean isEmpty() {
        return top.get() == null;
    }

    public int size() {
        int size = 0;
        Node<T> current = top.get();
        while (current != null) {
            size++;
            current = current.next;
        }
        return size;
    }


    public static void main(String[] args) {
        StackUsingLLAtomic<Integer> stack = new StackUsingLLAtomic<>();

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
