package org.concurrency.Concepts.CyclicBarrier.Program2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {
    private CyclicBarrier cyclicBarrier;
    private List<List<Integer>> partialResults = Collections.synchronizedList(new ArrayList<>());
    private Random random = new Random();
    private int NUM_PARTIAL_RESULTS;
    private int NUM_WORKERS;


    class NumberCruncherThread implements Runnable {

        @Override
        public void run() {
            String thisThreadName = Thread.currentThread().getName();
            List<Integer> partialResult = new ArrayList<>();

            // Crunch some numbers and store the partial result
            for (int i = 0; i < NUM_PARTIAL_RESULTS; i++) {
                Integer num = random.nextInt(10);
                System.out.println(thisThreadName
                        + ": Crunching some numbers! Final result - " + num);
                partialResult.add(num);
            }

            partialResults.add(partialResult);
            try {
                System.out.println(thisThreadName
                        + " waiting for others to reach barrier.");
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                // ...
            } catch (BrokenBarrierException e) {
                // ...
            }
        }
    }

    class AggregatorThread implements Runnable {

        @Override
        public void run() {

            String thisThreadName = Thread.currentThread().getName();

            System.out.println(
                    thisThreadName + ": Computing sum of " + NUM_WORKERS
                            + " workers, having " + NUM_PARTIAL_RESULTS + " results each.");
            int sum = 0;

            for (List<Integer> threadResult : partialResults) {
                System.out.print("Adding ");
                for (Integer partialResult : threadResult) {
                    System.out.print(partialResult+" ");
                    sum += partialResult;
                }
                System.out.println();
            }
            System.out.println(thisThreadName + ": Final result = " + sum);
        }
    }


    public void runSimulation(int numWorkers, int numberOfPartialResults) {
        NUM_PARTIAL_RESULTS = numberOfPartialResults;
        NUM_WORKERS = numWorkers;

        cyclicBarrier = new CyclicBarrier(NUM_WORKERS, new AggregatorThread());

        System.out.println("Spawning " + NUM_WORKERS
                + " worker threads to compute "
                + NUM_PARTIAL_RESULTS + " partial results each");

        for (int i = 0; i < NUM_WORKERS; i++) {
            Thread worker = new Thread(new NumberCruncherThread());
            worker.setName("Thread " + i);
            worker.start();
        }
    }

    public static void main(String[] args) {
        CyclicBarrierDemo demo = new CyclicBarrierDemo();
        demo.runSimulation(5, 3);
    }

    /*
    Spawning 5 worker threads to compute 3 partial results each
    Thread 4: Crunching some numbers! Final result - 8
    Thread 0: Crunching some numbers! Final result - 9
    Thread 2: Crunching some numbers! Final result - 8
    Thread 2: Crunching some numbers! Final result - 0
    Thread 2: Crunching some numbers! Final result - 8
    Thread 0: Crunching some numbers! Final result - 3
    Thread 0: Crunching some numbers! Final result - 1
    Thread 3: Crunching some numbers! Final result - 7
    Thread 3: Crunching some numbers! Final result - 8
    Thread 3: Crunching some numbers! Final result - 4
    Thread 4: Crunching some numbers! Final result - 7
    Thread 4: Crunching some numbers! Final result - 0
    Thread 2 waiting for others to reach barrier.
    Thread 4 waiting for others to reach barrier.
    Thread 0 waiting for others to reach barrier.
    Thread 1: Crunching some numbers! Final result - 0
    Thread 1: Crunching some numbers! Final result - 4
    Thread 1: Crunching some numbers! Final result - 4
    Thread 1 waiting for others to reach barrier.
    Thread 3 waiting for others to reach barrier.
    Thread 0: Computing sum of 5 workers, having 3 results each.
    Adding 8 0 8
    Adding 9 3 1
    Adding 7 8 4
    Adding 8 7 0
    Adding 0 4 4
    Thread 0: Final result = 71
     */

}
