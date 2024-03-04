package com.hendi.banktransfersystem.infrastructure.usertoken.dto;

import java.time.LocalDateTime;

import com.hendi.banktransfersystem.entity.user.model.UserAccountModel;
import com.hendi.banktransfersystem.usecase.usertoken.dto.IUserTokenCreateData;

public record UserTokenCreateData(
        UserAccountModel user,
        String token,
        LocalDateTime expiryDateTime) implements IUserTokenCreateData {
}
