package edu.roi.playbox.domain.dao;

import edu.roi.playbox.domain.SimpleUserEntityForDatabaseTest;

import javax.transaction.Transactional;
import java.util.List;

/**
 * #3
 * @author apavelchuk
 * @since 07.07.2015.
 */
@Transactional
public interface UserDao {

    SimpleUserEntityForDatabaseTest saveOrUpdate(SimpleUserEntityForDatabaseTest user);
    void remove(SimpleUserEntityForDatabaseTest user);
    List<SimpleUserEntityForDatabaseTest> findAll();
    List<SimpleUserEntityForDatabaseTest> findActive();

}
