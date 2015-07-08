package edu.roi.playbox.domain.dao;

import edu.roi.playbox.domain.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

/**
 * @author apavelchuk
 * @since 07.07.2015.
 */
/**
 * @author karlson35
 * @since 08.07.2015
 */
@Repository("userDao")
@Transactional
public class UserDaoImpl implements UserDao {

    public EntityManager em = Persistence.createEntityManagerFactory("COLIBRI").createEntityManager();

    @Override
    public User saveOrUpdate(User user) {
        return null;
    }

    @Override
    public void remove(User user) {

    }

    @Override
    public List<User> findAll() {
        TypedQuery<User> namedQuery = em.createNamedQuery("User.findAll",User.class);
        return namedQuery.getResultList();
    }

    @Override
    public List<User> findActive() {
        TypedQuery<User> namedQuery = em.createNamedQuery("User.findActive",User.class);
        return namedQuery.getResultList();
    }
}
