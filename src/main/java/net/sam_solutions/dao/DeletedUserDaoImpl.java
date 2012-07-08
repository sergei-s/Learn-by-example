package net.sam_solutions.dao;

import net.sam_solutions.model.DeletedUsersEntity;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * User: ssav
 * Date: 30.04.12
 */
@Repository
public class DeletedUserDaoImpl extends HibernateDaoSupport implements DeletedUserDao {
    @Autowired
    public void init(SessionFactory factory) {
        setSessionFactory(factory);
    }

    public List<DeletedUsersEntity> getAll() {
        return null;   //TODO d
    }

    public void create(DeletedUsersEntity deletedUsersEntity) {
        //TODO
    }

    public void delete(DeletedUsersEntity deletedUsersEntity) {
        //TODO
    }

    @Override
    public void update(DeletedUsersEntity deletedUsersEntity) {
        //TODO
    }
}
