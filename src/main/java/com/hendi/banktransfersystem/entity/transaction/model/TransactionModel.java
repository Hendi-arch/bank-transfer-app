package com.hendi.banktransfersystem.entity.transaction.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.hendi.banktransfersystem.entity.AbstractEntity;
import com.hendi.banktransfersystem.entity.user.model.UserModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class TransactionModel extends AbstractEntity<Long> {

    private UserModel sender;

    private UserModel receiver;

    private BigDecimal amount;

    private LocalDateTime timestamp;

    private String createdBy;

    private String updatedBy;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}
