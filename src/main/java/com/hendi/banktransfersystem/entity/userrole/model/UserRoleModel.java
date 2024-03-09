package com.hendi.banktransfersystem.entity.userrole.model;

import java.time.LocalDateTime;

import com.hendi.banktransfersystem.entity.AbstractEntity;
import com.hendi.banktransfersystem.infrastructure.config.db.schema.UserRoleSchema.RoleEnum;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class UserRoleModel extends AbstractEntity<Long> {

    public UserRoleModel(RoleEnum role) {
        this.role = role;
    }

    private RoleEnum role;

    private String createdBy;

    private String updatedBy;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
