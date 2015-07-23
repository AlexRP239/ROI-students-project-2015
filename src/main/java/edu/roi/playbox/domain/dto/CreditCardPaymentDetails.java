package edu.roi.playbox.domain.dto;


import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Max;
import java.util.Date;

/**
 * @author apavelchuk
 * @since 20.07.2015.
 */
public class CreditCardPaymentDetails extends PaymentDetailsDto {

    @NotEmpty @Max(50)
    private String firstName;

    @NotEmpty @Max(50)
    private String lastName;

    @NotEmpty @Max(18)
    private String cardNumber;

    @NotEmpty
    @DateTimeFormat(pattern = "MM/yyyy")
    private Date expirationDate;

    @NotEmpty
    @Max(4)
    private String validationCode;

    @Override
    public String getUserAccountId() {
        return null;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getValidationCode() {
        return validationCode;
    }

    public void setValidationCode(String validationCode) {
        this.validationCode = validationCode;
    }
}
