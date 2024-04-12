package org.concurrency.Questions.ImplementClientWrapper.Imp2;

public class Client {
    public void init() {
        // initialize connection to the server
        System.out.println("Client initialized");
    }

    public void request() {
        // make request to the server
        System.out.println("Client made request");
    }

    public void close() {
        // close the connection to the server
        System.out.println("Client closed");
    }
}
