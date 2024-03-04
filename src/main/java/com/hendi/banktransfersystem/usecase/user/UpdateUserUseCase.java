package com.hendi.banktransfersystem.usecase.user;

import java.math.BigDecimal;

import com.hendi.banktransfersystem.entity.user.gateway.UserGateway;
import com.hendi.banktransfersystem.entity.user.model.UserAccountModel;
import com.hendi.banktransfersystem.usecase.user.dto.IUserUpdateData;

public class UpdateUserUseCase {
    
    private final UserGateway userGateway;

    public UpdateUserUseCase(UserGateway userGateway) {
        this.userGateway = userGateway;
    }

    public UserAccountModel execute(IUserUpdateData data) {
        String username = data.username();
        String password = data.password();
        BigDecimal balance = data.balance();

        UserAccountModel userAccount = new UserAccountModel(username, password, balance);
        return userGateway.update(userAccount);
    }
    
}
