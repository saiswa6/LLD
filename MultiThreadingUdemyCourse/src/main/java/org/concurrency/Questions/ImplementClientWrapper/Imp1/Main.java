package org.concurrency.Questions.ImplementClientWrapper.Imp1;

public class Main {
    public static void main(String[] args) {
        ClientWrapper clientWrapper = new ClientWrapper();

        Thread t1 = new Thread(() -> {
            clientWrapper.init();
        });
    }
}
