package edu.roi.playbox.domain.dao;

import edu.roi.playbox.domain.Payment;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by karlson35 on 20.07.2015.
 */
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
}
