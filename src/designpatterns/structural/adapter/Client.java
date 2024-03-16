package src.designpatterns.structural.adapter;

public class Client {
    public static void main(String[] args) {

        /*
            // Assignment
            // 1. Implement PayUAdapter and use with FLipkart
            // 2. Implement Billdesk Codebase and adapter for the same
         */

        Flipkart flipkart = new Flipkart(new RazorpayPaymentGatewayAdapter());

        Flipkart flipkart1 = new Flipkart(new PayUPaymentGatewayAdapter());
    }
}
