package com.hendi.banktransfersystem.usecase.usertoken.dto;

import java.time.LocalDateTime;

import com.hendi.banktransfersystem.entity.user.model.UserAccountModel;

public interface IUserTokenCreateData {
    
    UserAccountModel user();

    String token();

    LocalDateTime expiryDateTime();

}
