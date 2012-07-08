package net.sam_solutions.service;

import net.sam_solutions.dao.PrivateInfoDao;
import net.sam_solutions.model.PrivateInfoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * User: ssav
 * Date: 30.04.12
 */
@Service
public class PrivateInfoServiceImpl implements PrivateInfoService {
    @Autowired
    private PrivateInfoDao privateInfoDao;

    public PrivateInfoDao getPrivateInfoDao() {
        return privateInfoDao;
    }

    public void setPrivateInfoDao(PrivateInfoDao privateInfoDao) {
        this.privateInfoDao = privateInfoDao;
    }

    @Transactional
    public List<PrivateInfoEntity> getAll() {
        return privateInfoDao.getAll();
    }

    @Transactional
    public void create(PrivateInfoEntity privateInfoEntity) {
        privateInfoDao.create(privateInfoEntity);
    }

    @Transactional
    public void delete(PrivateInfoEntity privateInfoEntity) {
        privateInfoDao.delete(privateInfoEntity);
    }

    @Transactional
    public void update(PrivateInfoEntity privateInfoEntity) {
        privateInfoDao.update(privateInfoEntity);
    }
}
