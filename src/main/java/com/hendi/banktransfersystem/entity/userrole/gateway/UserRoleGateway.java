package com.hendi.banktransfersystem.entity.userrole.gateway;

import java.util.List;
import java.util.Optional;

import com.hendi.banktransfersystem.entity.userrole.model.UserRoleModel;

public interface UserRoleGateway {

    UserRoleModel create(UserRoleModel userRoleModel);

    List<UserRoleModel> createAll(List<UserRoleModel> userRoleModels);

    Optional<UserRoleModel> findById(Long id);

    List<UserRoleModel> findAll();

}
