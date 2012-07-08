package net.sam_solutions.beans;

import net.sam_solutions.model.PublicInfoEntity;
import net.sam_solutions.service.PublicInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * User: Администратор
 * Date: 01.05.12
 */
@Component
@Scope(value="request")
public class PublicInfoBean {
    @Autowired
    private PublicInfoService publicInfoService;
    private List<PublicInfoEntity> publicInfoEntities;
    private PublicInfoEntity selectedItem = new PublicInfoEntity();

    public void setPublicInfoService(PublicInfoService publicInfoService) {
        this.publicInfoService = publicInfoService;
    }

    public PublicInfoEntity getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(PublicInfoEntity selectedItem) {
        this.selectedItem = selectedItem;
    }

    public List<PublicInfoEntity> getPublicInfoEntities() {
        publicInfoEntities = publicInfoService.getAll();
        return publicInfoEntities;
    }

    public String edit(){
        return "edit";
    }

    public String save(){
        publicInfoService.update(selectedItem);
        return "second";
    }
}
