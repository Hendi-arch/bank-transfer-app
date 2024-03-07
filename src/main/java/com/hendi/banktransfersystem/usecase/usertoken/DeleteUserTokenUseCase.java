package com.hendi.banktransfersystem.usecase.usertoken;

import com.hendi.banktransfersystem.entity.usertoken.gateway.UserTokenGateway;

import jakarta.transaction.Transactional;

public class DeleteUserTokenUseCase {
    
    private final UserTokenGateway userTokenGateway;

    public DeleteUserTokenUseCase(UserTokenGateway userTokenGateway) {
        this.userTokenGateway = userTokenGateway;
    }

    @Transactional
    public void execute(String token) {
        userTokenGateway.delete(token);
    }

}
