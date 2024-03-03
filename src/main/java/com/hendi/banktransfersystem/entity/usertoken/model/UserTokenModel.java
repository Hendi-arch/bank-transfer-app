package com.hendi.banktransfersystem.entity.usertoken.model;

import java.time.LocalDateTime;

import com.hendi.banktransfersystem.entity.AbstractEntity;
import com.hendi.banktransfersystem.entity.user.model.UserModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class UserTokenModel extends AbstractEntity<Long> {

    private UserModel user;

    private String token;

    private LocalDateTime expiryDateTime;

    private String createdBy;

    private String updatedBy;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}
