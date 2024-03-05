package com.hendi.banktransfersystem.entity.usertoken.model;

import java.time.LocalDateTime;

import com.hendi.banktransfersystem.entity.AbstractEntity;
import com.hendi.banktransfersystem.entity.user.model.UserAccountModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class UserTokenModel extends AbstractEntity<Long> {

    public UserTokenModel(
            UserAccountModel userAccount,
            String token,
            LocalDateTime expiryDateTime) {
        this.userAccount = userAccount;
        this.token = token;
        this.expiryDateTime = expiryDateTime;
    }

    private UserAccountModel userAccount;

    private String token;

    private LocalDateTime expiryDateTime;

    private String createdBy;

    private String updatedBy;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}
