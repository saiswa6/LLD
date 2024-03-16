package src.designpatterns.structural.adapter;

import src.designpatterns.structural.adapter.razorpay.RazorPaymentGateway;

public class RazorpayPaymentGatewayAdapter implements PaymentGateway{
    RazorPaymentGateway razorPaymentGateway = new RazorPaymentGateway();
    @Override
    public Long payViaCC(String cardNumber, int cvv, int expiryMonth, int expiryYear) {
        String cvvNumber = String.valueOf(cvv);
        String expiry = String.valueOf(expiryMonth) + "/" + String.valueOf(expiryYear);

        String answer = razorPaymentGateway.payByCreditCard(cardNumber,cvvNumber,expiry);
        return Long.parseLong(answer);
    }

    @Override
    public PaymentStatus getStatus(Long id) {
        boolean status = razorPaymentGateway.checkPaymentStatus(String.valueOf(id));
        if(status){
            return PaymentStatus.SUCCESS;
        }
        return PaymentStatus.FAILURE;
    }
}
