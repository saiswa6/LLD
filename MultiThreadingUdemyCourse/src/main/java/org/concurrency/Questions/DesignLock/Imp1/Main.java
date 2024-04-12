package org.concurrency.Questions.DesignLock.Imp1;

public class Main {
    public static void main(String[] args) {
        CustomLock customLock = new CustomLock();

        Thread t1 = new Thread(() -> {
            try {
                customLock.lock();
                System.out.println("Thread 1 is running.");
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                customLock.unlock();
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                Thread.sleep(1000);
                customLock.lock();
                System.out.println("Thread 2 is running.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                customLock.unlock();
            }
        });

        t1.start();
        t2.start();
    }
}

