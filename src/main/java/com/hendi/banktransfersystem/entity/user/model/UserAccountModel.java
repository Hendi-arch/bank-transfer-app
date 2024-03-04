package com.hendi.banktransfersystem.entity.user.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class UserAccountModel extends UserModel {

    public UserAccountModel(
            String username,
            String password,
            BigDecimal balance) {
        super(username, password);
        this.balance = balance;
    }

    private BigDecimal balance;

    private String createdBy;

    private String updatedBy;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}
