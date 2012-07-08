package net.sam_solutions.beans;

import net.sam_solutions.model.UsersEntity;
import net.sam_solutions.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

/**
 * User: ssav
 * Date: 03.05.12
 */
@Component
@Scope(value="request")
public class UsersTableData implements Serializable {
    @Autowired
    private UserService userService;
    private List<UsersEntity> usersEntities;
    private UsersEntity selectedItem = new UsersEntity();

    public UsersEntity getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(UsersEntity selectedItem) {
        this.selectedItem = selectedItem;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public List<UsersEntity> getUsersEntities() {
        usersEntities = userService.getAll();
        return usersEntities;
    }

    public String edit() {
        return "edit";
    }

    public String save() {
        userService.update(selectedItem);
        return "second";
    }

    public String deleteById() {
        userService.deleteById(selectedItem.getId());
        return "second";
    }


}
