package com.hendi.banktransfersystem.usecase.usertoken;

import java.time.LocalDateTime;

import com.hendi.banktransfersystem.entity.user.model.UserAccountModel;
import com.hendi.banktransfersystem.entity.usertoken.gateway.UserTokenGateway;
import com.hendi.banktransfersystem.entity.usertoken.model.UserTokenModel;
import com.hendi.banktransfersystem.usecase.usertoken.dto.IUserTokenCreateData;

public class CreateUserTokenUseCase {

    private final UserTokenGateway userTokenGateway;

    public CreateUserTokenUseCase(UserTokenGateway userTokenGateway) {
        this.userTokenGateway = userTokenGateway;
    }

    public UserTokenModel execute(IUserTokenCreateData data) {
        UserAccountModel user = data.user();
        String token = data.token();
        LocalDateTime expiryDateTime = data.expiryDateTime();

        UserTokenModel userToken = new UserTokenModel(user, token, expiryDateTime);
        return userTokenGateway.create(userToken);
    }

}
