package net.sam_solutions.dao;

import net.sam_solutions.model.DeletedUsersEntity;
import net.sam_solutions.model.UsersEntity;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

/**
 * User: ssav
 * Date: 25.04.12
 */
@Repository
public class UserDaoImpl extends HibernateDaoSupport implements UserDao {

    @Autowired
    public void init(SessionFactory factory) {
        setSessionFactory(factory);
    }

    public boolean checkUser(String login) {
        UsersEntity usersEntity = (UsersEntity)getSessionFactory().getCurrentSession().createQuery("FROM UsersEntity where login = ?").setParameter(0, login).uniqueResult();
        return (usersEntity != null);
    }

    public List<UsersEntity> getAll() {
        return getSessionFactory().getCurrentSession().createQuery("FROM UsersEntity U ORDER BY U.id ASC ").list();
    }

    public void create(UsersEntity usersEntity) {
        getSessionFactory().getCurrentSession().save(usersEntity);
    }

    public void delete(UsersEntity usersEntity) {
        //TODO
    }

    public void update(UsersEntity usersEntity) {
        getSessionFactory().getCurrentSession().update(usersEntity);
    }

    public void deleteById(long id) {
        UsersEntity userForDelete = getByID(id);
        DeletedUsersEntity deletedUsersEntity = new DeletedUsersEntity();
        deletedUsersEntity.setLogin(userForDelete.getLogin());
        deletedUsersEntity.setPassword(userForDelete.getPassword());
        getSessionFactory().getCurrentSession().save(deletedUsersEntity);
        getSessionFactory().getCurrentSession().delete(userForDelete);
    }

    public UsersEntity getByID(long id) {
        return (UsersEntity)getSessionFactory().getCurrentSession().get(UsersEntity.class, id);
    }

    public UsersEntity findByName(String name) {
        return (UsersEntity) getSessionFactory().getCurrentSession().createQuery("FROM UsersEntity" +
                " where login = ?").setParameter(0, name).uniqueResult();
    }
}
