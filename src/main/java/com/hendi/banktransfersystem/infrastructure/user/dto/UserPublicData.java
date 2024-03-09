package com.hendi.banktransfersystem.infrastructure.user.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.hendi.banktransfersystem.entity.user.model.UserAccountModel;
import com.hendi.banktransfersystem.entity.userrole.model.UserRoleModel;
import com.hendi.banktransfersystem.infrastructure.userrole.dto.UserRolePublicData;
import com.hendi.banktransfersystem.usecase.user.dto.IUserPublicData;

@JsonInclude(value = Include.NON_NULL)
public record UserPublicData(
        Long id,
        String username,
        BigDecimal balance,
        UserRolePublicData role,
        String jwtToken,
        LocalDateTime jwtExpiryDateTime,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) implements IUserPublicData {

    public UserPublicData(UserAccountModel data) {
        this(
                data.getId(),
                data.getUsername(),
                data.getBalance(),
                mapRole(data.getRole()),
                data.getJwtToken(),
                data.getJwtExpiryDateTime(),
                data.getCreatedAt(),
                data.getUpdatedAt());
    }

    private static UserRolePublicData mapRole(UserRoleModel data) {
        return new UserRolePublicData(data);
    }
}
