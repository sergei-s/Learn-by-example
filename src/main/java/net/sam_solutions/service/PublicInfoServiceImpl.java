package net.sam_solutions.service;

import net.sam_solutions.dao.PublicInfoDao;
import net.sam_solutions.model.PublicInfoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * User: ssav
 * Date: 30.04.12
 */
@Service
public class PublicInfoServiceImpl implements PublicInfoService {
    @Autowired
    private PublicInfoDao publicInfoDao;

    public PublicInfoDao getPublicInfoDao() {
        return publicInfoDao;
    }

    public void setPublicInfoDao(PublicInfoDao publicInfoDao) {
        this.publicInfoDao = publicInfoDao;
    }

    @Transactional
    public List<PublicInfoEntity> getAll() {
        return publicInfoDao.getAll();
    }

    @Transactional
    public void create(PublicInfoEntity publicInfoEntity) {
        publicInfoDao.create(publicInfoEntity);
    }

    @Transactional
    public void delete(PublicInfoEntity publicInfoEntity) {
        publicInfoDao.delete(publicInfoEntity);
    }

    @Transactional
    public void update(PublicInfoEntity publicInfoEntity) {
        publicInfoDao.update(publicInfoEntity);
    }
}
