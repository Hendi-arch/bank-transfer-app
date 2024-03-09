package com.hendi.banktransfersystem.usecase.userrole;

import com.hendi.banktransfersystem.entity.userrole.exception.UserRoleNotFoundException;
import com.hendi.banktransfersystem.entity.userrole.gateway.UserRoleGateway;
import com.hendi.banktransfersystem.entity.userrole.model.UserRoleModel;

public class GetUserRoleUseCase {

    private final UserRoleGateway userRoleGateway;

    public GetUserRoleUseCase(UserRoleGateway userRoleGateway) {
        this.userRoleGateway = userRoleGateway;
    }

    public UserRoleModel execute(Long id) throws UserRoleNotFoundException {
        return userRoleGateway
                .findById(id)
                .orElseThrow(UserRoleNotFoundException::new);
    }

}
