package org.example.module39.Ex3;

public class SharedResource {
    boolean itemAvailable = false;

    //synchronized put the monitor lock
    public synchronized void addItem(){
        itemAvailable = true;
        System.out.println("Item added by thread "+ Thread.currentThread().getName()+ " and invoking all threads which are waiting");
        notifyAll();
    }

    public synchronized void consumeItem(){
        System.out.println("Consume Item method invoked by "+ Thread.currentThread().getName());

        //Using while loop to avoid "spurious wake up", some times because of system noise
        while (!itemAvailable){
            try{
                System.out.println("Thread "+ Thread.currentThread().getName() + " is waiting now");
                wait(); // it releases monitor lock
            } catch (Exception e){

            }
        }
        System.out.println("Item consumed by "+ Thread.currentThread().getName());
        itemAvailable = false;
    }
}
