package com.hendi.banktransfersystem.entity.user.gateway;

import java.util.Optional;
import java.util.List;

import com.hendi.banktransfersystem.entity.user.exception.UserNotFoundException;
import com.hendi.banktransfersystem.entity.user.model.UserAccountModel;

public interface UserGateway {

    UserAccountModel create(UserAccountModel userAccountModel);

    UserAccountModel update(UserAccountModel userAccountModel) throws UserNotFoundException;

    void delete(Long id) throws UserNotFoundException;

    Optional<UserAccountModel> findById(Long id) throws UserNotFoundException;

    Optional<UserAccountModel> findByUsername(String username) throws UserNotFoundException;

    List<UserAccountModel> findAll();

}
