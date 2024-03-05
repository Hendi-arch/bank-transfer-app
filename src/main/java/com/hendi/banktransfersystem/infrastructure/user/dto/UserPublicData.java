package com.hendi.banktransfersystem.infrastructure.user.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.hendi.banktransfersystem.entity.user.model.UserAccountModel;
import com.hendi.banktransfersystem.usecase.user.dto.IUserPublicData;

@JsonInclude(value = Include.NON_NULL)
public record UserPublicData(
        Long id,
        String username,
        BigDecimal balance,
        String jwtToken,
        LocalDateTime jwtExpiryDateTime,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) implements IUserPublicData {

    public UserPublicData(UserAccountModel data) {
        this(
                data.getId(),
                data.getUsername(),
                data.getBalance(),
                data.getJwtToken(),
                data.getJwtExpiryDateTime(),
                data.getCreatedAt(),
                data.getUpdatedAt());
    }
}
