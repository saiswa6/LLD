package org.example.BoundedBuffer;

public class BoundedBufferMain {
    public static void main(String[] args) {
        BoundedBuffer buffer = new BoundedBuffer();

        // Producer thread
        Thread producer = new Thread(() -> {
            try {
                for (int i = 1; i <= 20; i++) {
                    buffer.produce(i);
                    //Thread.sleep(100); // Simulate production time
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        // Consumer thread
        Thread consumer = new Thread(() -> {
            try {
                for (int i = 1; i <= 20; i++) {
                    buffer.consume();
                    //Thread.sleep(200); // Simulate consumption time
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        producer.start();
        consumer.start();
    }
}
