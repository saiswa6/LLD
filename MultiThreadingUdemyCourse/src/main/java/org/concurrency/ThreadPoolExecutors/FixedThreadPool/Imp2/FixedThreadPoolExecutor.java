//package org.concurrency.ThreadPoolExecutors.FixedThreadPool.Imp2;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.concurrent.BlockingQueue;
//import java.util.concurrent.Callable;
//import java.util.concurrent.Future;
//import java.util.concurrent.LinkedBlockingQueue;
//import java.util.concurrent.atomic.AtomicInteger;
//
//public class FixedThreadPoolExecutor implements CustomExecutorService{
//    private final int poolSize;
//    private final List<WorkerThread> workers;
//    private final BlockingQueue<Runnable> taskQueue;
//    private volatile boolean isShutdown = false;
//    private volatile boolean isShutdownNow = false;
//    private final AtomicInteger runningCount = new AtomicInteger(0);
//
//    public FixedThreadPoolExecutor(int poolSize) {
//        this.poolSize = poolSize;
//        this.taskQueue = new LinkedBlockingQueue<>();
//        this.workers = new ArrayList<>(poolSize);
//
//        for (int i = 0; i < poolSize; i++) {
//            WorkerThread worker = new WorkerThread();
//            workers.add(worker);
//            worker.start();
//        }
//    }
//
//    @Override
//    public void execute(Runnable command) {
//        if (isShutdown) {
//            throw new IllegalStateException("Executor has been shutdown");
//        }
//
//        synchronized (taskQueue) {
//            taskQueue.add(command);
//            taskQueue.notify();
//        }
//    }
//
//    @Override
//    public Future<?> submit(Runnable task) {
//        FutureTask<Void> future = new FutureTask<>(task, null);
//        execute(future);
//        return future;
//    }
//
//    @Override
//    public <T> Future<T> submit(Runnable task, T result) {
//        FutureTask<T> future = new FutureTask<>(task, result);
//        execute(future);
//        return future;
//    }
//
//    @Override
//    public <T> Future<T> submit(Callable<T> task) {
//        FutureTask<T> future = new FutureTask<>(task);
//        execute(future);
//        return future;
//    }
//
//    @Override
//    public void shutdown() {
//        isShutdown = true;
//    }
//
//    @Override
//    public List<Runnable> shutdownNow() {
//        isShutdownNow = true;
//        isShutdown = true;
//
//        List<Runnable> tasks = new ArrayList<>();
//        synchronized (taskQueue) {
//            taskQueue.drainTo(tasks);
//        }
//
//        for (WorkerThread worker : workers) {
//            worker.interrupt();
//        }
//
//        return tasks;
//    }
//
//    @Override
//    public boolean isShutdown() {
//        return isShutdown;
//    }
//
//    @Override
//    public boolean isTerminated() {
//        return isShutdown && runningCount.get() == 0;
//    }
//
//    @Override
//    public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
//        long endTime = System.currentTimeMillis() + unit.toMillis(timeout);
//        for (WorkerThread worker : workers) {
//            worker.join(unit.toMillis(timeout));
//        }
//        return System.currentTimeMillis() >= endTime;
//    }
//
//    private class WorkerThread extends Thread {
//        @Override
//        public void run() {
//            Runnable task;
//            runningCount.incrementAndGet();
//
//            while (!isShutdownNow) {
//                synchronized (taskQueue) {
//                    while (taskQueue.isEmpty() && !isShutdown) {
//                        try {
//                            taskQueue.wait();
//                        } catch (InterruptedException e) {
//                            if (isShutdownNow) {
//                                break;
//                            }
//                        }
//                    }
//                    if (isShutdownNow) {
//                        break;
//                    }
//                    task = taskQueue.poll();
//                }
//
//                if (task != null) {
//                    try {
//                        task.run();
//                    } catch (RuntimeException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//
//            runningCount.decrementAndGet();
//        }
//    }
//
//    private static class FutureTask<T> implements Future<T> {
//        private T result;
//        private final Runnable runnable;
//        private final T value;
//        private boolean isDone = false;
//
//        public FutureTask(Runnable runnable, T result) {
//            this.runnable = runnable;
//            this.value = result;
//        }
//
//        public FutureTask(Callable<T> callable) {
//            this.runnable = () -> {
//                try {
//                    result = callable.call();
//                } catch (Exception e) {
//                    throw new RuntimeException(e);
//                }
//            };
//            this.value = null;
//        }
//
//        @Override
//        public boolean cancel(boolean mayInterruptIfRunning) {
//            // Not implemented
//            return false;
//        }
//
//        @Override
//        public boolean isCancelled() {
//            // Not implemented
//            return false;
//        }
//
//        @Override
//        public boolean isDone() {
//            return isDone;
//        }
//
//        @Override
//        public T get() throws InterruptedException, ExecutionException {
//            if (!isDone) {
//                synchronized (this) {
//                    wait();
//                }
//            }
//            return result;
//        }
//
//        @Override
//        public T get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
//            long endTime = System.currentTimeMillis() + unit.toMillis(timeout);
//            if (!isDone) {
//                synchronized (this) {
//                    wait(unit.toMillis(timeout));
//                }
//                if (!isDone && System.currentTimeMillis() >= endTime) {
//                    throw new TimeoutException();
//                }
//            }
//            return result;
//        }
//
//        public void run() {
//            try {
//                runnable.run();
//                synchronized (this) {
//                    isDone = true;
//                    notifyAll();
//                }
//            } catch (Exception e) {
//                throw new RuntimeException(e);
//            }
//        }
//    }
//
//    public static void main(String[] args) throws InterruptedException {
//        FixedThreadPoolExecutor executor = new FixedThreadPoolExecutor(3);
//
//        for (int i = 0; i < 5; i++) {
//            int taskId = i;
//            executor.execute(() -> {
//                System.out.println("Task " + taskId + " is executing by " + Thread.currentThread().getName());
//                try {
//                    Thread.sleep(1000); // Simulate some work
//                } catch (InterruptedException e) {
//                    Thread.currentThread().interrupt();
//                }
//            });
//        }
//
//        // Graceful shutdown
//        executor.shutdown();
//        if (executor.awaitTermination(5, TimeUnit.SECONDS)) {
//            System.out.println("Executor terminated gracefully");
//        } else {
//            System.out.println("Executor shutdown timed out");
//        }
//    }
//}
