package org.concurrency.Questions.ImplementClientWrapper.Imp2;



/*ReentrantLock by itself doesn't allow multiple concurrent calls to the request() method because it's a mutual exclusion lock. To enable concurrent calls to request() after successful initialization, we can use a Semaphore to control the concurrency.

Here's the revised code using a Semaphore to allow concurrent calls to request():*/

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;



public class ClientWrapper extends Client {
    private final Lock lock = new ReentrantLock();
    private final Semaphore requestSemaphore = new Semaphore(1);
    private final Object closeLock = new Object();
    private boolean isInitialized = false;
    private boolean isClosed = false;

    @Override
    public void init() {
        lock.lock();
        try {
            while (isInitialized) {
                lock.unlock();
                synchronized (closeLock) {
                    closeLock.wait();
                }
                lock.lock();
            }
            super.init();
            isInitialized = true;
            synchronized (closeLock) {
                closeLock.notifyAll();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void request() {
        try {
            requestSemaphore.acquire();
            while (!isInitialized) {
                synchronized (closeLock) {
                    closeLock.wait();
                }
            }
            super.request();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            requestSemaphore.release();
        }
    }

    @Override
    public void close() {
        lock.lock();
        try {
            while (isClosed) {
                lock.unlock();
                synchronized (closeLock) {
                    closeLock.wait();
                }
                lock.lock();
            }
            super.close();
            isClosed = true;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        ClientWrapper clientWrapper = new ClientWrapper();

        // Initialize the client
        clientWrapper.init();

        // Make a request
        clientWrapper.request();

        // Close the connection
        clientWrapper.close();
    }
}

