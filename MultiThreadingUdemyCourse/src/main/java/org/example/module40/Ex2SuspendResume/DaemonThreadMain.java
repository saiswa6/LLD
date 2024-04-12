package org.example.module40.Ex2SuspendResume;

public class DaemonThreadMain {
    public static void main(String[] args) {
        SharedResource sharedResource = new SharedResource();

        System.out.println("Main thread started");
        Thread t1 = new Thread(
                ()->{
                    System.out.println("Thread1 calling produce method");
                    sharedResource.produce();
                }
        );

        t1.setDaemon(true);
        t1.start();


        try{
            Thread.sleep(1000);
        } catch (InterruptedException e){
            throw  new RuntimeException(e);
        }

        System.out.println("Main thread is finishing its work");

        /*
        Main thread started
        Thread1 calling produce method
        Lock acquired by Thread-0
        Main thread is finishing its work

        // T1 lock is not released but t1 is finished as it is daemon thread.
         */


        /*
        t1.start();
        t1.setDaemon(true);
        If order of setDaemon changes,

        Main thread started
        Thread1 calling produce method
        Lock acquired by Thread-0
        Exception in thread "main" java.lang.IllegalThreadStateException
            at java.base/java.lang.Thread.setDaemon(Thread.java:1414)
            at org.example.module40.Ex2SuspendResume.DaemonThreadMain.main(DaemonThreadMain.java:17)
        Lock released by Thread-0
         */
    }
}
