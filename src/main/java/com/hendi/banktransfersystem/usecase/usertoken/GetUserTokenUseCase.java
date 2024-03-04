package com.hendi.banktransfersystem.usecase.usertoken;

import com.hendi.banktransfersystem.entity.usertoken.exception.UserTokenNotFoundException;
import com.hendi.banktransfersystem.entity.usertoken.gateway.UserTokenGateway;
import com.hendi.banktransfersystem.entity.usertoken.model.UserTokenModel;

public class GetUserTokenUseCase {

    private final UserTokenGateway userTokenGateway;

    public GetUserTokenUseCase(UserTokenGateway userTokenGateway) {
        this.userTokenGateway = userTokenGateway;
    }

    public UserTokenModel execute(String authToken) throws UserTokenNotFoundException {
        return userTokenGateway.findUserToken(authToken).orElseThrow(UserTokenNotFoundException::new);
    }

}
