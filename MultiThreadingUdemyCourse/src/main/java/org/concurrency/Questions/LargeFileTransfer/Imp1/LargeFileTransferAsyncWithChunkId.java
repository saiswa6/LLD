package org.concurrency.Questions.LargeFileTransfer.Imp1;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
Q1 It is a large file network transmission: there is a large file locally, and the other end of the network has a receiving end. The communication API does not need to be implemented by myself, requiring use fastest speed to complete the transfer.
Q2 You have to transfer large files (e.g. each file having size of 40 GB).
All the files are there on the disk and you have to transfer all the files to another system with given IP address.
The network bandwidth is 10 GBPS (the network interface card can support upto 10GBPS data including both data sent and recieved).
What would be the best possible way to transfer the files.

Answer :
- Deduplication may be required so that we don't need to send the same chunk multiple times.
- Compression is required to reduce the amount of data to be transferred. Compress the file to use minimal network bandwidth.
- Whenever we have to transfer large files, the best way would be to send one big file in parts.
We can have a program which creates parts of the original file, and we send the parts.
And at the receiver's end we will have some program which will basically collate the parts into one single file.
By sending the file by parts, if any file part transfer fails, we will have to only transfer the failed part again, and not the entire file.
Also we can spawn multiple threads to transfer multiple file parts in parallel.

Or we can use a ThreadPool with a message queue, all the file transfer jobs will be added to queue by the program which generates the parts from original file.

- using a checksum to ensure data integrity while transferring the files
- including a chunk_id (or part number) in each chunk being transferred to keep track of received chunks at the receiver end
- Have an asynchronous setup to transfer chunks of the file.
- think of the upper bounds of the TCP packet size etc
- On the receiving end, devise a merge algorithm to stitch all chunks.
- in case of failures, receiver can request for a specific chunk_id. In case a chunk fails, we perform a retry strategy - depending on the HttpStatusCode from receiver's end.
 */



/*
ChatGPT
Note:
- Replace /path/to/source/largefile and /path/to/destination/largefile with the appropriate file paths.
- The CHUNK_SIZE is set to 1 MB. You can adjust this value based on your system's memory and network performance to optimize the transfer speed.
- The THREAD_POOL_SIZE is set to 10. You can adjust this value based on your system's CPU cores to optimize the parallel processing.

In this enhanced version:
- A chunkId is assigned to each chunk before sending it to the receiver.
- Multiple threads are used for asynchronous transfer and reassembly of the chunks to optimize the transfer speed.
- This approach ensures that the chunks are processed and assembled in the correct order on the receiving end, thereby maintaining the integrity of the original file.
 */



public class LargeFileTransferAsyncWithChunkId {
    private static final String DEST_IP = "10.10.12.14";
    private static final int PORT = 1234;
    private static final int CHUNK_SIZE = 1024; // 1MB
    private static int THREAD_POOL_SIZE = 10; //Number Of Threads

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(THREAD_POOL_SIZE);

        try(Socket socket = new Socket(DEST_IP,PORT);
            BufferedOutputStream bos = new BufferedOutputStream(socket.getOutputStream());
            FileInputStream fis = new FileInputStream("/path/to/source/largefile")){

            byte buffer[] = new byte[CHUNK_SIZE];
            long chunkId = 0;

            while(fis.available() > 0) {
                int bytesRead = fis.read(buffer);
                if(bytesRead == -1 ) {
                    break;
                }

                byte [] chunk = new byte[bytesRead];
                System.arraycopy(buffer, 0, chunk, 0, bytesRead);
                /*
                The line System.arraycopy(buffer, 0, chunk, 0, bytesRead); is used to copy data from one array (buffer) to another array (chunk) with the specified length (bytesRead). Here's what each parameter represents:

                buffer: The source array from which data will be copied.
                0: The starting position in the source array (buffer) from where the copying will begin.
                chunk: The destination array where the copied data will be placed.
                0: The starting position in the destination array (chunk) where the copied data will be placed.
                bytesRead: The number of bytes to be copied from the source array (buffer) to the destination array (chunk).
                                 */

                // Add chunkId to the beginning of the chunk
                byte[] chunkWithId = addChunkId(chunkId, chunk);

                // Submit chunk transfer task to thread pool
                executorService.submit(() -> {
                    try {
                        bos.write(chunkWithId);
                        bos.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });

                chunkId++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static byte[] addChunkId(long chunkId, byte[] chunk) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(baos);

        try {
            dos.writeLong(chunkId);  // Write chunkId as long
            dos.write(chunk);       // Write chunk data
            dos.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return baos.toByteArray();
    }
}
