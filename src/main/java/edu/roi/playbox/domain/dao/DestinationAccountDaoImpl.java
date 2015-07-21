package edu.roi.playbox.domain.dao;

import edu.roi.playbox.domain.DestinationAccount;
import edu.roi.playbox.domain.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by olezhek on 20.07.2015.
 */


@Repository("DestinationAccountDao")
@Transactional
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
        TypedQuery<DestinationAccount> namedQuery = em.createNamedQuery("User.findActive",DestinationAccount.class);
        return namedQuery.getResultList();
    }
}
