package net.sam_solutions.service;

import net.sam_solutions.dao.UserDao;
import net.sam_solutions.model.UsersEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * User: ssav
 * Date: 30.04.12
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Transactional
    public boolean checkUser(String login) {
        return userDao.checkUser(login);
    }

    @Transactional
    public List<UsersEntity> getAll() {
        return userDao.getAll();
    }

    @Transactional
    public void create(UsersEntity usersEntity) {
        userDao.create(usersEntity);
    }

    @Transactional
    public void delete(UsersEntity usersEntity) {
        userDao.delete(usersEntity);
    }

    @Transactional
    public void update(UsersEntity usersEntity) {
        userDao.update(usersEntity);
    }

    @Transactional
    public void deleteById(long id) {
        userDao.deleteById(id);
    }
}
