package net.sam_solutions.service;

import net.sam_solutions.model.UsersEntity;

/**
 * User: ssav
 * Date: 30.04.12
 */
public interface UserService extends GenericService<UsersEntity>{
    boolean checkUser(String login);
    void update(UsersEntity usersEntity);
    void deleteById(long id);
}
