package com.hendi.banktransfersystem.entity.usertoken.gateway;

import com.hendi.banktransfersystem.entity.usertoken.exception.UserTokenNotFoundException;
import com.hendi.banktransfersystem.entity.usertoken.model.UserTokenModel;

public interface UserTokenGateway {

    UserTokenModel create(UserTokenModel userTokenModel);

    UserTokenModel update(UserTokenModel userTokenModel) throws UserTokenNotFoundException;

    void delete(String authToken) throws UserTokenNotFoundException;

    UserTokenModel findUserToken(String authToken) throws UserTokenNotFoundException;

}
