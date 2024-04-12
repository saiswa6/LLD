package org.example.module46.Ex1ThreadPoolExecutor;

import java.util.concurrent.ThreadFactory;

public class CustomThreadFactory implements ThreadFactory {
    @Override
    public Thread newThread(Runnable r) {
        /*Thread t = new Thread(r);
        t.setDaemon(false);
        t.setPriority(Thread.NORM_PRIORITY);
        return t;*/

        Thread t = newThread(r);
        t.setPriority(5);
        t.setDaemon(false);
        return t;
    }
}
