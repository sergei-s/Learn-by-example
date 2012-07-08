package net.sam_solutions.beans;

import net.sam_solutions.service.DeletedUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * User: Администратор
 * Date: 01.05.12
 */
@Component
@Scope(value="request")
public class DeletedUsersBean implements Serializable {
    @Autowired
    private DeletedUserService deletedUserService;
    private String name;
    private String password;

    public void setDeletedUserService(DeletedUserService deletedUserService) {
        this.deletedUserService = deletedUserService;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
