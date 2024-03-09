package com.hendi.banktransfersystem.usecase.userrole;

import java.util.List;

import com.hendi.banktransfersystem.entity.userrole.gateway.UserRoleGateway;
import com.hendi.banktransfersystem.entity.userrole.model.UserRoleModel;

public class SearchUserRoleUseCase {

    private final UserRoleGateway userRoleGateway;

    public SearchUserRoleUseCase(UserRoleGateway userRoleGateway) {
        this.userRoleGateway = userRoleGateway;
    }

    public List<UserRoleModel> execute() {
        return userRoleGateway.findAll();
    }

}
