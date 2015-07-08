package edu.roi.playbox.domain.dao;

import edu.roi.playbox.domain.User;

import javax.transaction.Transactional;
import java.util.List;

/**
 * #3
 * @author apavelchuk
 * @since 07.07.2015.
 */
@Transactional
public interface UserDao {

    User saveOrUpdate(User user);
    void remove(User user);
    List<User> findAll();
    List<User> findActive();

}
