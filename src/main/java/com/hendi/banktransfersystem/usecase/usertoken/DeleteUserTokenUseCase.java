package com.hendi.banktransfersystem.usecase.usertoken;

import com.hendi.banktransfersystem.entity.usertoken.gateway.UserTokenGateway;

public class DeleteUserTokenUseCase {
    
    private final UserTokenGateway userTokenGateway;

    public DeleteUserTokenUseCase(UserTokenGateway userTokenGateway) {
        this.userTokenGateway = userTokenGateway;
    }

    public void execute(String token) {
        userTokenGateway.delete(token);
    }

}
