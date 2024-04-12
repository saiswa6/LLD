package org.concurrency.ThreadPoolExecutors.FixedThreadPool.Imp1;

public class CustomExecutors {
    public static CustomExecutorService newFixedThreadPool (int fixedThreadPoolSizeRequired) {
        return new CustomThreadPoolExecutor(fixedThreadPoolSizeRequired);
    }
}
