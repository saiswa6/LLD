package org.concurrency.Questions.ImplementStackUsingLinkedList.Imp1;


/*
 I had to implement thread-safe stack using a linkedlist. Push and pop should be O(1). This problem is pretty similar to Design Bounded Blocking Queue.
 Interviewer asked some additional questions prying into my knowledge of concurrency primitives.  (https://leetcode.com/problems/design-  ()-blocking-queue/description/)  (https://leetcode.com/discuss/interview-question/1457983/Rubrik-or-Software-Engineer-(Entry-Level)-or-Palo-Alto-or-September-2021-or-Reject)
 */

import java.util.List;

/*
By synchronizing these methods, we ensure that multiple threads can safely access and modify the stack without causing race conditions or other concurrency issues.
This ensures that only one thread can execute a synchronized method on the stack object at a time,
preventing race conditions and ensuring thread safety.
 */

class ListNode{
    int value;
    ListNode next;
    public ListNode(int value) {
        this.value = value;
    }

}
public class StackUsingLinkedList {
    private ListNode top;
    int size = 0;



    public synchronized void push(int item){
        ListNode newNode = new ListNode(item);
        newNode.next = top;
        top = newNode;
        size++;
    }

    public synchronized int pop(){
        if(isEmpty()) {
            throw new java.util.NoSuchElementException("Stack is emoty");
        }

        int item = top.value;
        top = top.next;
        size--;
        return item;
    }

    public synchronized int peek(){
        if(isEmpty()) {
            throw new java.util.NoSuchElementException("Stack is emoty");
        }

        int item = top.value;
        return item;
    }


    public synchronized  boolean isEmpty(){
        return top == null;
    }

    public synchronized int size(){
        return size;
    }

    public static void main(String[] args) {
        StackUsingLinkedList stack = new StackUsingLinkedList();
        stack.push(1);
        stack.push(2);
        stack.push(3);

        System.out.println(stack.pop());
        System.out.println(stack.size());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        /*
        3
        2
        2
        1
         */

    }
}
