package org.concurrency.Questions.DesignSequenceGenerator.Imp1;

import java.util.concurrent.atomic.AtomicInteger;

/*

In Java, a sequence generator is a utility to generate a series of unique numbers or identifiers in a specific order. It is commonly used in database applications to generate unique primary keys or in other scenarios where a unique sequence of numbers is required.

- We use AtomicInteger to ensure that the getNext() method is thread-safe.
- The getNext() method atomically increments and returns the next sequence number.
- This SequenceGenerator will produce a series of unique integers starting from 1 and incrementing by 1 each time getNext() is called.
 */

public class SequenceGenerator {
    private final AtomicInteger counter = new AtomicInteger(0);

    /**
     * Generates the next sequence number.
     *
     * @return the next sequence number
     */
    public int getNext() {
        return counter.incrementAndGet();
    }

    public static void main(String[] args) {
        SequenceGenerator generator = new SequenceGenerator();

        // Generate some sequence numbers
        for (int i = 0; i < 10; i++) {
            System.out.println("Next sequence number: " + generator.getNext());
        }
    }
}

