package org.example.module39.Ex2;

public class MonitorLockExample {
    public synchronized void task1(){

        try {
            System.out.println("inside task1");
            Thread.sleep(10000);
            System.out.println("task1 completed");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void task2(){
        System.out.println("task2, but before synchronized");
        synchronized (this){ // this is current object
            System.out.println("task2, inside synchronization");
        }
    }

    public void task3(){
        System.out.println("inside task3");
    }
}
