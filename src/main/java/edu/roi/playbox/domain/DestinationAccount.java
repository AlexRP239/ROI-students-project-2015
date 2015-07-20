package edu.roi.playbox.domain;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by karlson35 on 19.07.2015.
 */

@Entity
@Table(name = "DestinationAccount")

@NamedQueries({
        @NamedQuery(name = "DestinationAccount.findEnabled", query = "SELECT c from DestinationAccount c WHERE c.isTrue(enabled)")
})

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
