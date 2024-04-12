package org.concurrency.Questions.LargeFileTransfer.Imp1;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LargeFileReceiverAsyncWithChunkId {

    private static final int PORT = 12345;
    private static final int CHUNK_SIZE = 1024 * 1024; // 1 MB
    private static final int THREAD_POOL_SIZE = 10; // Number of threads

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(THREAD_POOL_SIZE);

        try (ServerSocket serverSocket = new ServerSocket(PORT);
             Socket socket = serverSocket.accept();
             BufferedInputStream bis = new BufferedInputStream(socket.getInputStream());
             FileOutputStream fos = new FileOutputStream("/path/to/destination/largefile")) {

            byte[] buffer = new byte[CHUNK_SIZE];
            long expectedChunkId = 0;

            while (true) {
                int bytesRead = bis.read(buffer, 0, buffer.length);
                if (bytesRead == -1) {
                    break;
                }

                // Extract chunkId and data from the received chunk
                ByteArrayInputStream bais = new ByteArrayInputStream(buffer);
                DataInputStream dis = new DataInputStream(bais);

                long receivedChunkId = dis.readLong();
                byte[] chunkData = new byte[bytesRead - Long.BYTES];
                dis.readFully(chunkData);

                if (receivedChunkId != expectedChunkId) {
                    System.err.println("Unexpected chunkId: " + receivedChunkId);
                    continue;
                }

                // Submit chunk assembly task to thread pool
                executorService.submit(() -> {
                    try {
                        fos.write(chunkData);
                        fos.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });

                expectedChunkId++;
            }

            // Shutdown the executor service
            executorService.shutdown();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
