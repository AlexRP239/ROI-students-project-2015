package edu.roi.playbox.domain;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by karlson35 on 19.07.2015.
 */

@Entity
@Table(name = "Customer")

@NamedQueries({
        @NamedQuery(name = "Customer.findAll", query = "SELECT c from Customer c"),
        @NamedQuery(name = "Customer.findActive", query = "SELECT c from Customer c WHERE " +
                "c.expired<CURRENT_TIMESTAMP and (c.blocked = FALSE or c.blocked is null)"),
        @NamedQuery(name = "Customer.findById", query = "SELECT c FROM Customer c WHERE c.id = :id")
})
public class Customer {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "businessName")
    private String businessName;

    @Column(name = "secretKey", nullable = false)
    private String secretKey;

    @Column(name = "contact")
    private String contact;

    @Column(name = "blocked", nullable = false)
    private Boolean blocked;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "expired")
    private Date expired;

    @Column(name = "notificationAddress")
    private String notificationAddress;


    @Column(name = "notificationMethod")
    @Enumerated(EnumType.STRING)
    private NotificationMethod notificationMethod;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public Boolean getBlocked() {
        return blocked;
    }

    public void setBlocked(Boolean blocked) {
        this.blocked = blocked;
    }

    public Date getExpired() {
        return expired;
    }

    public void setExpired(Date expired) {
        this.expired = expired;
    }

    public String getNotificationAddress() {
        return notificationAddress;
    }

    public void setNotificationAddress(String notificationAddress) {
        this.notificationAddress = notificationAddress;
    }

    public NotificationMethod getNotificationMethod() {
        return notificationMethod;
    }

    public void setNotificationMethod(NotificationMethod notificationMethod) {
        this.notificationMethod = notificationMethod;
    }


}


