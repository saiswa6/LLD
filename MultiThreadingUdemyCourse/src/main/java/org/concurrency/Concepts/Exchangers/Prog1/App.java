package org.concurrency.Concepts.Exchangers.Prog1;

import java.util.concurrent.Exchanger;


import java.util.concurrent.Exchanger;

/**
 * With the help of Exchanger -> two threads can exchange objects
 *
 * exchange() -> exchanging objects is done via one of the two exchange()
 * methods
 *
 * 	For example: genetic algorithms, training neural networks
 *
 */

class FirstWorker implements Runnable {

    private int counter;
    private Exchanger<Integer> exchanger;

    public FirstWorker(Exchanger<Integer> exchanger) {
        this.exchanger = exchanger;
    }

    @Override
    public void run() {

        while (true) {

            counter = counter + 1;
            System.out.println("FirstWorker incremented the counter: " + counter);

            try {
                counter = exchanger.exchange(counter);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class SecondWorker implements Runnable {

    private int counter;
    private Exchanger<Integer> exchanger;

    public SecondWorker(Exchanger<Integer> exchanger) {
        this.exchanger = exchanger;
    }

    @Override
    public void run() {

        while (true) {

            counter = counter - 1;
            System.out.println("SecondWorker decremented the counter: " + counter);

            try {
                counter = exchanger.exchange(counter);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class App {

    public static void main(String[] args) {

        Exchanger<Integer> exchanger = new Exchanger<>();

        new Thread(new FirstWorker(exchanger)).start();
        new Thread(new SecondWorker(exchanger)).start();

    }
    /*
    FirstWorker incremented the counter: 1
    SecondWorker decremented the counter: -1
    FirstWorker incremented the counter: 0
    SecondWorker decremented the counter: 0
    FirstWorker incremented the counter: 1
    SecondWorker decremented the counter: -1
    FirstWorker incremented the counter: 0
    SecondWorker decremented the counter: 0
    FirstWorker incremented the counter: 1
    SecondWorker decremented the counter: -1
    FirstWorker incremented the counter: 0
    SecondWorker decremented the counter: 0
    FirstWorker incremented the counter: 1
    SecondWorker decremented the counter: -1
    FirstWorker incremented the counter: 0
    SecondWorker decremented the counter: 0
    FirstWorker incremented the counter: 1
    SecondWorker decremented the counter: -1
    FirstWorker incremented the counter: 0
    SecondWorker decremented the counter: 0
    FirstWorker incremented the counter: 1
    SecondWorker decremented the counter: -1
    FirstWorker incremented the counter: 0
    SecondWorker decremented the counter: 0
    FirstWorker incremented the counter: 1
    SecondWorker decremented the counter: -1
    FirstWorker incremented the counter: 0
     */
}
