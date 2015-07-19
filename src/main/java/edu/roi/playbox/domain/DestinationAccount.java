package edu.roi.playbox.domain;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by karlson35 on 19.07.2015.
 */

@Entity
@Table(name = "DestinationAccount")

enum PaymentMethod {
    CREDIT_CARD,
    BANK_ACCOUNT;

    @Column(name = "displayName")
    private String displayName;
    @Column(name = "htmlTemplateName")
    private String htmlTemplateName;
    @Column(name = "serviceBeanName")
    private String serviceBeanName;

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getServiceBeanName() {
        return serviceBeanName;
    }

    public void setServiceBeanName(String serviceBeanName) {
        this.serviceBeanName = serviceBeanName;
    }

    public String getHtmlTemplateName() {
        return htmlTemplateName;
    }

    public void setHtmlTemplateName(String htmlTemplateName) {
        this.htmlTemplateName = htmlTemplateName;
    }
}

public class DestinationAccount {

    @Id
    @GeneratedValue
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "paymentMethod")
    private PaymentMethod paymentMethod;
    @Column(name = "amount",precision = 10, scale = 2)
    private BigDecimal amount;
    @Column(name = "maxAccountAmount",precision = 10, scale = 2)
    private BigDecimal maxAccountAmount;
    @Column(name = "maxPaymentAmount",precision = 10, scale = 2)
    private BigDecimal maxPaymentAmount;
    @Column(name = "enabled")
    private Boolean enabled;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public BigDecimal getMaxPaymentAmount() {
        return maxPaymentAmount;
    }

    public void setMaxPaymentAmount(BigDecimal maxPaymentAmount) {
        this.maxPaymentAmount = maxPaymentAmount;
    }

    public BigDecimal getMaxAccountAmount() {
        return maxAccountAmount;
    }

    public void setMaxAccountAmount(BigDecimal maxAccountAmount) {
        this.maxAccountAmount = maxAccountAmount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

}
