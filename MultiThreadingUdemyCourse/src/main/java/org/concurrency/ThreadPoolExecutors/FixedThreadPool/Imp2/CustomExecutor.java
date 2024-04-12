package org.concurrency.ThreadPoolExecutors.FixedThreadPool.Imp2;

public interface CustomExecutor {

    void execute(Runnable command); //submit method is not present in Executor interface in real java
}
