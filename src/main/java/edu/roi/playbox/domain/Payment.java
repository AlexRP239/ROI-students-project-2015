package edu.roi.playbox.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by karlson35 on 19.07.2015.
 */

@Entity
@Table(name = "Payment", uniqueConstraints={@UniqueConstraint(columnNames={"customerId", "invoiceId"})})

enum PaymentStatus {
    INITIATED,
    POSTED,
    COMPLETED,
    ERROR,
    AUTHORIZATION_REQUIRED,
    CLEARED
}


public class Payment {

    @Id
    @GeneratedValue
    private Long id;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    @Column(name = "invoiceId")
    private String invoiceId;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "customer")
    private Customer customer;

    @Column(name = "account")
    private DestinationAccount account;

    @Column(name = "status")
    private PaymentStatus status;

    @Column(name = "userAccountId")
    private String userAccountId;

    @Column(name = "created")
    private Date created;

    @Column(name = "updated")
    private Date updated;

    @Column(name = "errorDescription")
    private String errorDescription;

    @Column(name = "transactionID")
    private String transactionID;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(String invoiceId) {
        this.invoiceId = invoiceId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public DestinationAccount getAccount() {
        return account;
    }

    public void setAccount(DestinationAccount account) {
        this.account = account;
    }

    public String getUserAccountId() {
        return userAccountId;
    }

    public void setUserAccountId(String userAccountId) {
        this.userAccountId = userAccountId;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public String getErrorDescription() {
        return errorDescription;
    }

    public void setErrorDescription(String errorDescription) {
        this.errorDescription = errorDescription;
    }

    public String getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(String transactionID) {
        this.transactionID = transactionID;
    }


    public PaymentStatus getStatus() {
        return status;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
    }


}
