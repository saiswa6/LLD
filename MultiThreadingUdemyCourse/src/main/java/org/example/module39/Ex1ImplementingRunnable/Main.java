package org.example.module39.Ex1ImplementingRunnable;

public class Main {
    public static void main(String[] args) {
        System.out.println("Going inside " + Thread.currentThread().getName());
        MultiThreadingLearning runnableObject = new MultiThreadingLearning();
        Thread thread = new Thread(runnableObject);
        thread.start();
        System.out.println("Finish main method "+ Thread.currentThread().getName());
    }
}
