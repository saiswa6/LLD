package org.example.module42.Ex7Condition;

public class ConditionMain {
    public static void main(String[] args) {
        SharedResource sharedResource = new SharedResource();

        Thread t1 = new Thread(
                () -> {
                    for(int i = 0; i < 2; i++){
                        sharedResource.producer();
                    }
                }
        );

        Thread t2 = new Thread(
                () -> {
                    for(int i = 0; i < 2; i++){
                        sharedResource.consume();
                    }
                }
        );

        t1.start();
        t2.start();

        /*
        Check once

        Producer Lock acquired by Thread-0
        Producer Lock released by Thread-0
        Producer Lock acquired by Thread-0
        Producer thread is waiting Thread-0
        Consumer Lock acquired by Thread-1
        Consumer Lock released by Thread-1
        Producer Lock released by Thread-0
        Consumer Lock acquired by Thread-1
        Consumer Lock released by Thread-1
         */
    }
}
