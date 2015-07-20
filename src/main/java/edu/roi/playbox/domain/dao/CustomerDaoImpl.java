package edu.roi.playbox.domain.dao;

import edu.roi.playbox.domain.Customer;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by karlson35 on 20.07.2015.
 */
@Repository("customerDao")
@Transactional
public class CustomerDaoImpl implements CustomerDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Customer saveOrUpdate(Customer customer) {
        if (customer.getId() == null) {
            em.persist(customer);
        } else {
            customer = em.merge(customer);
        }
        return customer;
    }

    public Customer findById(Long customerId){
        return null;
    }

    public Customer findActive(Long customerId){
        return null;
    }

    public List<Customer> findAll(){
        return null;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }



}
