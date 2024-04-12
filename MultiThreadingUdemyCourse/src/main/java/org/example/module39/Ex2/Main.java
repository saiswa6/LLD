package org.example.module39.Ex2;

public class Main {
    public static void main(String[] args) {
        MonitorLockExample obj = new MonitorLockExample();
        Thread t1 = new Thread(
                ()-> {
                    obj.task1();
                }
        );

        Thread t2 = new Thread(
                ()-> {
                    obj.task2();
                }
        );

        Thread t3 = new Thread(
                ()-> {
                    obj.task3();
                }
        );

        t1.start();
        t2.start();
        t3.start();
        /*
        inside task3
        inside task1
        task2, but before synchronized
        task1 completed
        task2, inside synchronization
         */

        /*
        Normal way to create thread
        --------------------------------
        class MonitorThread1 implements Runnable{
        MonitorLockExample obj;
        MonitorThread1(MonitorLockExample obj){
          this.obj = obj;
        }

        public void run(){
          obj.task1();
        }
         */
    }
}
