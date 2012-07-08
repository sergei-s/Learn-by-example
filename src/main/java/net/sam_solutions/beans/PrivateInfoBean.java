package net.sam_solutions.beans;

import net.sam_solutions.model.PrivateInfoEntity;
import net.sam_solutions.service.PrivateInfoService;
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
public class PrivateInfoBean {
    @Autowired
    private PrivateInfoService privateInfoService;
    private List<PrivateInfoEntity> privateInfoEntities;

    public void setPrivateInfoService(PrivateInfoService privateInfoService) {
        this.privateInfoService = privateInfoService;
    }

    public List<PrivateInfoEntity> getPrivateInfoEntities() {
        privateInfoEntities = privateInfoService.getAll();
        return privateInfoEntities;
    }
}
