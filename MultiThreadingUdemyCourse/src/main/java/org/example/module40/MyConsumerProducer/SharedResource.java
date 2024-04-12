package org.example.module40.MyConsumerProducer;


import java.util.LinkedList;
import java.util.Queue;

public class SharedResource {
   Queue<Integer> buffer = new LinkedList<>();

   public synchronized void addItem(){
       while (!buffer.isEmpty()){
           try {
               System.out.println("Inside add item waiting");
               wait();
           } catch (InterruptedException e) {
               throw new RuntimeException(e);
           }
       }

       while (buffer.size() != 5){
           buffer.add(1);
       }
       System.out.println("Inside add item buffer added and notifying");

       notifyAll();
   }

    public synchronized void consumeItem(){
        while (buffer.isEmpty()){
            try {
                System.out.println("Inside consume item waiting");
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        while (!buffer.isEmpty()){
            buffer.remove();
        }
        System.out.println("Inside consume item buffer removed and notifying");

        notifyAll();;
    }

}
