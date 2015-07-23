package edu.roi.playbox.domain.dao;

import edu.roi.playbox.domain.Customer;
import edu.roi.playbox.domain.Payment;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * Created by karlson35 on 20.07.2015.
 */
@Repository("PaymentDao")
@Transactional
public class PaymentDaoImpl implements PaymentDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Payment saveOrUpdate(Payment customer) {
        if (customer.getId() == null) {
            em.persist(customer);
        } else {
            customer = em.merge(customer);
        }
        return customer;
    }

    @Override
    public Optional<Payment> findByInvoiceAndCustomer(Customer customer, String invoiceId) {
        List<Payment> paymentList = em.createQuery("select p from Payment p where p.invoiceId = :invoiceId and p.customer = :customer", Payment.class)
                .setParameter("invoiceId", invoiceId)
                .setParameter("customer", customer)
                .getResultList();

        if (paymentList.isEmpty()) {
            return Optional.empty();
        } else if (paymentList.size() == 1){
            return Optional.of(paymentList.get(0));
        } else {
            throw new IllegalStateException("Unexpected exception only one payment is expected");
        }
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }
}
