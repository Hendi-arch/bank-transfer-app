package com.hendi.banktransfersystem.usecase.user;

import java.util.List;

import com.hendi.banktransfersystem.entity.user.gateway.UserGateway;
import com.hendi.banktransfersystem.entity.user.model.UserAccountModel;

public class SearchUserUseCase {
    
    private final UserGateway userGateway;

    public SearchUserUseCase(UserGateway userGateway) {
        this.userGateway = userGateway;
    }

    public List<UserAccountModel> execute() {
        return userGateway.findAll();
    }

}
