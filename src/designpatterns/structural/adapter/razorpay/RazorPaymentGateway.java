package src.designpatterns.structural.adapter.razorpay;

public class RazorPaymentGateway {

    public String payByCreditCard(String creditCard, String cvv, String expiry){
        System.out.println("Payment done by Razorpay");
        return "123";
    }

    public boolean checkPaymentStatus(String id){
        return false;
    }
}
