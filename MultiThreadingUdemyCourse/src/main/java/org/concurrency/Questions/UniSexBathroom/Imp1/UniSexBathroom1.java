package org.concurrency.Questions.UniSexBathroom.Imp1;

import java.util.concurrent.Semaphore;
/*
Check educative tutorial
 */

public class UniSexBathroom1 {
    private String WOMEN = "women";
    private String MEN = "men";
    private String NONE = "none";

    String inUseBy = NONE;
    private int employeesInBathroom = 0;

    Semaphore maxEmployees = new Semaphore(3);
    public void useBathroom(String name) {
        System.out.println(name + " using Bathromm, Current employees in bathroom : " + employeesInBathroom);
        try{
            Thread.sleep(3000);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(name + " done using Bathromm");
    }

    public void maleUseBathroom(String name) throws InterruptedException {
        synchronized (this) {
            while (inUseBy.equals(WOMEN)) {
                this.wait();
            }
            //maxEmployees.acquire();
            employeesInBathroom++;
            inUseBy = MEN;
        }
        maxEmployees.acquire();

        useBathroom(name);
        maxEmployees.release();

        synchronized (this) {
            employeesInBathroom--;

            if(employeesInBathroom == 0){
                inUseBy = NONE;
            }
            this.notifyAll();
        }
    }

    public void femaleUseBathroom(String name) throws InterruptedException {
        synchronized (this) {
            while (inUseBy.equals(MEN)) {
                this.wait();
            }
            maxEmployees.acquire();
            employeesInBathroom++;
            inUseBy = WOMEN;
        }

        useBathroom(name);
        maxEmployees.release();

        synchronized (this) {
            employeesInBathroom--;

            if(employeesInBathroom == 0){
                inUseBy = NONE;
            }
            this.notifyAll();
        }
    }

    public static void main( String args[] ) throws InterruptedException {
        final UniSexBathroom1 unisexBathroom = new UniSexBathroom1();
        Thread female1 = new Thread(new Runnable() {
            public void run() {
                try {
                    unisexBathroom.femaleUseBathroom("Lisa");
                } catch (InterruptedException ie) {
                }
            }
        });
        Thread male1 = new Thread(new Runnable() {
            public void run() {
                try {
                    unisexBathroom.maleUseBathroom("John");
                } catch (InterruptedException ie) {
                }
            }
        });
        Thread male2 = new Thread(new Runnable() {
            public void run() {
                try {
                    unisexBathroom.maleUseBathroom("Bob");
                } catch (InterruptedException ie) {
                }
            }
        });
        Thread male3 = new Thread(new Runnable() {
            public void run() {
                try {
                    unisexBathroom.maleUseBathroom("Anil");
                } catch (InterruptedException ie) {
                }
            }
        });
        Thread male4 = new Thread(new Runnable() {
            public void run() {
                try {
                    unisexBathroom.maleUseBathroom("Wentao");
                } catch (InterruptedException ie) {
                }
            }
        });
        female1.start();
        male1.start();
        male2.start();
        male3.start();
        male4.start();
        female1.join();
        male1.join();
        male2.join();
        male3.join();
        male4.join();
    }

    /*
    John using Bathromm, Current employees in bathroom : 1
    Anil using Bathromm, Current employees in bathroom : 3
    Bob using Bathromm, Current employees in bathroom : 2
    Anil done using Bathromm
    John done using Bathromm
    Wentao using Bathromm, Current employees in bathroom : 2
    Bob done using Bathromm
    Wentao done using Bathromm
    Lisa using Bathromm, Current employees in bathroom : 1
    Lisa done using Bathromm
     */

}
