package org.example.module39.BaeldungSynchronized;

public class Main {
    public static void main(String[] args) {
        SynchronizedMethods synchronizedMethods = new SynchronizedMethods();

        Thread t1 = new Thread(() -> {
            synchronizedMethods.calculateSum();
        });

        Thread t2 = new Thread(() -> {
            synchronizedMethods.calculateSum();
        });

        Thread t3 = new Thread(() -> {
            synchronizedMethods.calculateSum();
        });

        t1.start();
        t2.start();
        t3.start();

        try{
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Sum " + synchronizedMethods.getSum());

        Object lock = new Object();
        synchronizedMethods.print(lock);

    }
}
