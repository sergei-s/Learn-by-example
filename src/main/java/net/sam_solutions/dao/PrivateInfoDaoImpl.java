package net.sam_solutions.dao;

import net.sam_solutions.model.PrivateInfoEntity;
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
public class PrivateInfoDaoImpl extends HibernateDaoSupport implements PrivateInfoDao {
    @Autowired
    public void init(SessionFactory factory) {
        setSessionFactory(factory);
    }

    @Override
    public List<PrivateInfoEntity> getAll() {
        return getSessionFactory().getCurrentSession().createQuery("FROM PrivateInfoEntity P ORDER BY P.id ASC ").list();
    }

    @Override
    public void create(PrivateInfoEntity privateInfoEntity) {
        //TODO
    }

    @Override
    public void delete(PrivateInfoEntity privateInfoEntity) {
        //TODO
    }

    @Override
    public void update(PrivateInfoEntity privateInfoEntity) {
        //TODO
    }
}
