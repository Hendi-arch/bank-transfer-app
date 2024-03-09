package com.hendi.banktransfersystem.infrastructure.userrole.dto;

import java.time.LocalDateTime;

import com.hendi.banktransfersystem.entity.userrole.model.UserRoleModel;
import com.hendi.banktransfersystem.usecase.userrole.dto.IUserRolePublicData;

public record UserRolePublicData(
        Long id,
        String role,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) implements IUserRolePublicData {

    public UserRolePublicData(UserRoleModel data) {
        this(
                data.getId(),
                data.getRole().toString(),
                data.getCreatedAt(),
                data.getUpdatedAt());
    }

}
