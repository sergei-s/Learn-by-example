package net.sam_solutions.dao;

import net.sam_solutions.model.UsersEntity;

/**
 * User: ssav
 * Date: 30.04.12
 */

public interface UserDao extends GenericDao<UsersEntity> {
    boolean checkUser(String login);
    void update(UsersEntity usersEntity);
    void deleteById(long id);
    UsersEntity getByID(long id);
    UsersEntity findByName(String name);
}
