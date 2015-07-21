package edu.roi.playbox.domain.dao;

import edu.roi.playbox.domain.Payment;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

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
}
