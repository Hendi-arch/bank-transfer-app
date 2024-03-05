package com.hendi.banktransfersystem.infrastructure.usertoken.dto;

import java.time.LocalDateTime;

import com.hendi.banktransfersystem.entity.usertoken.model.UserTokenModel;
import com.hendi.banktransfersystem.usecase.usertoken.dto.IUserTokenPublicData;

import jakarta.validation.constraints.NotBlank;

public record UserTokenPublicData(
        @NotBlank String token,
        LocalDateTime expiryDateTime) implements IUserTokenPublicData {

    public UserTokenPublicData(UserTokenModel data) {
        this(data.getToken(), data.getExpiryDateTime());
    }
}
