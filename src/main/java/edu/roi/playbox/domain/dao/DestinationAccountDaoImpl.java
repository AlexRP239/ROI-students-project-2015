package edu.roi.playbox.domain.dao;

import edu.roi.playbox.domain.DestinationAccount;
import edu.roi.playbox.domain.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by olezhek on 20.07.2015.
 */



public class DestinationAccountDaoImpl implements DestinationAccountDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public DestinationAccount saveOrUpdate(DestinationAccount customer) {
        if (customer.getId() == null) {
            em.persist(customer);
        } else {

            customer = em.merge(customer);
        }
        return customer;
    }

    @Override
    public List<DestinationAccount> findEnabled() {
        return null;
    }
}
