package org.concurrency.Concepts.BlockingQueue.Program1ConPro;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Main {
    public static void main(String[] args) {
        BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(10);

        ProducerThread producerThread = new ProducerThread(queue);
        ConsumerThread consumerThread = new ConsumerThread(queue);

        new Thread(producerThread).start();
        new Thread(consumerThread).start();
    }

    /*
    Taking item from the queue ... 0
    Putting item into the queue ... 0
    Putting item into the queue ... 1
    Taking item from the queue ... 1
    Putting item into the queue ... 2
    Putting item into the queue ... 3
    Taking item from the queue ... 2
    Putting item into the queue ... 4
    Putting item into the queue ... 5
    Taking item from the queue ... 3
    Putting item into the queue ... 6
    Putting item into the queue ... 7
    Taking item from the queue ... 4
    Putting item into the queue ... 8
    Putting item into the queue ... 9
    Taking item from the queue ... 5
    Putting item into the queue ... 10
    Putting item into the queue ... 11
    Taking item from the queue ... 6
    Putting item into the queue ... 12
    Putting item into the queue ... 13
    Taking item from the queue ... 7
    Putting item into the queue ... 14
    Taking item from the queue ... 8
    Putting item into the queue ... 15
    Putting item into the queue ... 16
    Taking item from the queue ... 9
    Putting item into the queue ... 17
    Putting item into the queue ... 18
    Taking item from the queue ... 10
    Putting item into the queue ... 19
    Putting item into the queue ... 20
    Taking item from the queue ... 11
    Putting item into the queue ... 21
    Taking item from the queue ... 12
    Putting item into the queue ... 22
    Taking item from the queue ... 13
    Putting item into the queue ... 23
    Putting item into the queue ... 24
    Taking item from the queue ... 14
    Taking item from the queue ... 15
    Putting item into the queue ... 25
    Taking item from the queue ... 16
    Putting item into the queue ... 26
    Taking item from the queue ... 17
    Putting item into the queue ... 27
    Taking item from the queue ... 18
    Putting item into the queue ... 28
     */
}
