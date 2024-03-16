package src.designpatterns.structural.adapter;

public interface PaymentGateway {

    Long payViaCC(String cardNumber,int cvv, int expiryMonth, int expiryYear);

    PaymentStatus getStatus(Long id);
}
