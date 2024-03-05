package com.hendi.banktransfersystem.usecase.user;

import java.math.BigDecimal;

import com.hendi.banktransfersystem.entity.user.gateway.UserGateway;
import com.hendi.banktransfersystem.entity.user.model.UserAccountModel;
import com.hendi.banktransfersystem.usecase.user.dto.IUserCreateData;

public class CreateUserUseCase {

    private final UserGateway userGateway;

    public CreateUserUseCase(UserGateway userGateway) {
        this.userGateway = userGateway;
    }

    public UserAccountModel execute(IUserCreateData data) {
        String username = data.username();
        String password = data.password();
        BigDecimal balance = data.balance();

        UserAccountModel userAccount = new UserAccountModel(username, password, balance);
        return userGateway.create(userAccount);
    }

}
