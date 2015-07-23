package edu.roi.playbox.domain.dto;

import edu.roi.playbox.domain.Payment;

/**
 * @author apavelchuk
 * @since 20.07.2015.
 */
public abstract class PaymentDetailsDto {

    private Payment payment;

    public PaymentDetailsDto() {
    }

    public PaymentDetailsDto(Payment payment) {
        this.payment = payment;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public abstract String getUserAccountId();
}
