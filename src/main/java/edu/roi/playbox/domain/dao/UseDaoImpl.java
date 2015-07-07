package edu.roi.playbox.domain.dao;

import edu.roi.playbox.domain.SimpleUserEntityForDatabaseTest;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author apavelchuk
 * @since 07.07.2015.
 */
@Repository
@Transactional
public class UseDaoImpl implements UserDao {
    @Override
    public SimpleUserEntityForDatabaseTest saveOrUpdate(SimpleUserEntityForDatabaseTest user) {
        return null;
    }

    @Override
    public void remove(SimpleUserEntityForDatabaseTest user) {

    }

    @Override
    public List<SimpleUserEntityForDatabaseTest> findAll() {
        return null;
    }

    @Override
    public List<SimpleUserEntityForDatabaseTest> findActive() {
        return null;
    }
}
