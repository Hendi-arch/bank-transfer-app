package com.hendi.banktransfersystem.infrastructure.usertoken.dto;

import java.time.LocalDateTime;

import com.hendi.banktransfersystem.entity.usertoken.model.UserTokenModel;
import com.hendi.banktransfersystem.usecase.usertoken.dto.IUserTokenPublicData;

public record UserTokenPublicData(
        String token,
        LocalDateTime expiryDateTime) implements IUserTokenPublicData {

    public UserTokenPublicData(UserTokenModel data) {
        this(data.getToken(), data.getExpiryDateTime());
    }
}
