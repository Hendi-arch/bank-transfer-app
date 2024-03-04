package com.hendi.banktransfersystem.infrastructure.user.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.hendi.banktransfersystem.entity.user.model.UserAccountModel;
import com.hendi.banktransfersystem.usecase.user.dto.IUserPublicData;

public record UserPublicData(
        Long id,
        String username,
        BigDecimal balance,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) implements IUserPublicData {

    public UserPublicData(UserAccountModel data) {
        this(data.getId(), data.getUsername(), data.getBalance(), data.getCreatedAt(), data.getUpdatedAt());
    }
}
