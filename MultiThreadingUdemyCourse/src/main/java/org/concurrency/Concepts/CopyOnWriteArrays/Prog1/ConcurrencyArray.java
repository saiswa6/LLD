package org.concurrency.Concepts.CopyOnWriteArrays.Prog1;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ConcurrencyArray {

    public static void main(String[] args) {
        List<Integer> list = new CopyOnWriteArrayList<>();
        list.addAll(Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0, 0, 0));


        Thread t1 = new Thread(new WriteTask(list));
        Thread t2 = new Thread(new WriteTask(list));
        Thread t3 = new Thread(new WriteTask(list));
        Thread t4 = new Thread(new ReadTask(list));

        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }
    /*
    [0, 0, 7, 2, 0, 0, 0, 0, 0, 0]
    [0, 0, 7, 2, 0, 0, 0, 0, 0, 0]
    [0, 7, 1, 1, 8, 0, 0, 0, 0, 0]
    [0, 0, 1, 1, 8, 0, 0, 0, 0, 0]
    [0, 0, 1, 1, 4, 0, 0, 6, 0, 0]
    [0, 0, 1, 1, 4, 0, 0, 8, 5, 0]
    [1, 7, 1, 2, 4, 3, 0, 8, 5, 0]
    [1, 7, 1, 2, 4, 2, 0, 8, 5, 0]
    [1, 9, 1, 2, 7, 2, 0, 8, 0, 0]
    [1, 9, 2, 2, 7, 2, 8, 8, 0, 9]
    [1, 9, 2, 2, 7, 2, 4, 8, 0, 7]
    [1, 9, 2, 2, 7, 5, 4, 9, 0, 7]
    [1, 9, 9, 2, 7, 5, 7, 9, 0, 7]
    [1, 4, 9, 2, 1, 5, 7, 4, 0, 7]
    [1, 4, 1, 2, 1, 5, 7, 8, 0, 3]
    [1, 0, 1, 2, 1, 5, 6, 8, 0, 3]
    [1, 0, 1, 2, 8, 5, 2, 8, 7, 3]
    [1, 6, 1, 4, 8, 5, 0, 8, 7, 3]
    [1, 6, 1, 4, 8, 4, 0, 0, 7, 3]
    [1, 6, 1, 4, 8, 2, 0, 8, 7, 7]
    [1, 6, 1, 3, 8, 2, 0, 5, 7, 7]
    [1, 6, 1, 3, 8, 2, 0, 5, 7, 7]
    [1, 6, 1, 3, 8, 2, 9, 4, 7, 7]
    [1, 6, 1, 3, 8, 2, 4, 4, 8, 7]
    [1, 2, 1, 3, 8, 2, 4, 4, 6, 7]
    [9, 2, 1, 3, 8, 2, 4, 4, 9, 7]
    [2, 2, 1, 2, 8, 2, 4, 4, 9, 7]
    [2, 2, 5, 2, 6, 2, 4, 4, 4, 7]
    [2, 2, 5, 2, 6, 2, 4, 0, 5, 7]
    [3, 4, 6, 2, 6, 2, 4, 0, 5, 7]
    [3, 4, 6, 3, 6, 2, 4, 0, 9, 7]
     */
}
