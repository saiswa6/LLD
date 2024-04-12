package org.concurrency.ThreadPoolExecutors.FixedThreadPool.Imp1;

public interface CustomExecutorService extends CustomExecutor{
    void shutdown();
    void shutdownNow();

    void submit(Runnable task);

    //isTerminated() -  Returns true if all tasks have completed following shut down.
    // 	isShutdown() -  Returns true if this executor has been shut down.
    //	submit(Callable<T> task) - Submits a value-returning task for execution and returns a Future representing the pending results of the task.
    // 	submit(Runnable task) -     //Submits a Runnable task for execution and returns a Future representing that task.
    //  <T> Future<T>	submit(Runnable task, T result) - Submits a Runnable task for execution and returns a Future representing that task.


}
