package net.sam_solutions.service;

import net.sam_solutions.dao.DeletedUserDao;
import net.sam_solutions.model.DeletedUsersEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * User: ssav
 * Date: 30.04.12
 */
@Service
public class DeletedUserServiceImpl implements DeletedUserService {
    @Autowired
    private DeletedUserDao deletedUserDao;

    public DeletedUserDao getDeletedUserDao() {
        return deletedUserDao;
    }

    public void setDeletedUserDao(DeletedUserDao deletedUserDao) {
        this.deletedUserDao = deletedUserDao;
    }

    @Transactional
    public List<DeletedUsersEntity> getAll() {
        return deletedUserDao.getAll();
    }

    @Transactional
    public void create(DeletedUsersEntity deletedUsersEntity) {
        deletedUserDao.delete(deletedUsersEntity);
    }

    @Transactional
    public void delete(DeletedUsersEntity deletedUsersEntity) {
        deletedUserDao.create(deletedUsersEntity);
    }

    @Transactional
    public void update(DeletedUsersEntity deletedUsersEntity) {
        deletedUserDao.update(deletedUsersEntity);
    }
}
