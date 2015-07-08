package edu.roi.playbox.domain.dao;

import edu.roi.playbox.domain.User;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author apavelchuk
 * @since 07.07.2015.
 */
@Repository("userDao")
@Transactional
public class UserDaoImpl implements UserDao {

    @Override
    public User saveOrUpdate(User user) {
        return null;
    }

    @Override
    public void remove(User user) {

    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public List<User> findActive() {
        return null;
    }
}
