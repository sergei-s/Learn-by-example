package net.sam_solutions.dao;

import net.sam_solutions.model.PublicInfoEntity;
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
public class PublicInfoDaoImpl extends HibernateDaoSupport implements PublicInfoDao {
    @Autowired
    public void init(SessionFactory factory) {
        setSessionFactory(factory);
    }

    @Override
    public List<PublicInfoEntity> getAll() {
        return getSessionFactory().getCurrentSession().createQuery("FROM PublicInfoEntity P ORDER BY P.id ASC ").list();
    }

    @Override
    public void create(PublicInfoEntity publicInfoEntity) {
        //TODO
    }

    @Override
    public void delete(PublicInfoEntity publicInfoEntity) {
        //TODO
    }

    @Override
    public void update(PublicInfoEntity publicInfoEntity) {
        getSessionFactory().getCurrentSession().update(publicInfoEntity);
    }
}
