package com.hendi.banktransfersystem.entity.usertoken.gateway;

import java.util.Optional;

import com.hendi.banktransfersystem.entity.usertoken.model.UserTokenModel;

public interface UserTokenGateway {

    UserTokenModel create(UserTokenModel userTokenModel);

    void delete(String authToken);

    Optional<UserTokenModel> findUserToken(String authToken);

}
