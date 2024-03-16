package src.designpatterns.structural.adapter;

import src.designpatterns.structural.adapter.payu.PayUGatewayPayment;
import src.designpatterns.structural.adapter.payu.PayUPaymentStatus;

public class PayUPaymentGatewayAdapter implements PaymentGateway{
    PayUGatewayPayment payUGatewayPayment = new PayUGatewayPayment();
    @Override
    public Long payViaCC(String cardNumber, int cvv, int expiryMonth, int expiryYear) {
        Long cardNum = Long.parseLong(cardNumber);
        Long cvvNum = Long.valueOf(cvv);
        Long expiry = Long.valueOf(expiryMonth) + Long.valueOf(expiryMonth);
        payUGatewayPayment.makeCCPayment(cardNum, cvvNum, expiry);
        Long tId = Long.valueOf(123);
        return tId;
    }

    @Override
    public PaymentStatus getStatus(Long id) {
        PayUPaymentStatus payUPaymentStatus = payUGatewayPayment.checkPaymentStatus(String.valueOf(id));
        if(payUPaymentStatus == PayUPaymentStatus.SUCCESS){
            return PaymentStatus.SUCCESS;
        } else if (payUPaymentStatus == PayUPaymentStatus.FAILURE || payUPaymentStatus == PayUPaymentStatus.ERROR || payUPaymentStatus == PayUPaymentStatus.UNKNOWN || payUPaymentStatus == PayUPaymentStatus.TIMEOUT) {
            return PaymentStatus.FAILURE;
        } else if (payUPaymentStatus == PayUPaymentStatus.PENDING) {
            return PaymentStatus.PENDING;
        }
        return PaymentStatus.FAILURE;
    }
}
