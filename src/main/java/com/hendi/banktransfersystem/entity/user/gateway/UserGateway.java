package com.hendi.banktransfersystem.entity.user.gateway;

import java.util.Optional;
import java.util.List;

import com.hendi.banktransfersystem.entity.user.exception.UserNotFoundException;
import com.hendi.banktransfersystem.entity.user.model.UserAccountModel;
import com.hendi.banktransfersystem.entity.user.model.UserModel;

public interface UserGateway {

    UserModel login(UserModel userModel) throws UserNotFoundException;

    UserModel logout();

    UserAccountModel create(UserAccountModel userAccountModel);

    UserAccountModel update(UserAccountModel userAccountModel) throws UserNotFoundException;

    void delete(Long id) throws UserNotFoundException;

    Optional<UserAccountModel> findById(Long id) throws UserNotFoundException;

    List<UserAccountModel> findAll();

}
