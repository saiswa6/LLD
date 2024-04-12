package org.concurrency.ThreadPoolExecutors.ScheduledThreadPool;

import java.sql.Time;
import java.util.concurrent.TimeUnit;

public interface CustomScheduledExecutorService {
    void submit(Runnable r, int delay, TimeUnit timeUnit);
    void scheduleAtFixedRate(Runnable r, int initialDelay, int subsequentDay, TimeUnit timeUnit);
    void scheduleAtFixedDelay(Runnable r, int initialDelay, int subsequentDelay, TimeUnit timeUnit);

    void shutDown();
}
