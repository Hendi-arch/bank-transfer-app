package com.hendi.banktransfersystem.entity.transaction.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.hendi.banktransfersystem.entity.AbstractEntity;
import com.hendi.banktransfersystem.entity.user.model.UserAccountModel;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class TransactionModel extends AbstractEntity<Long> {

    public TransactionModel(
            UserAccountModel sender,
            UserAccountModel receiver,
            BigDecimal amount,
            LocalDateTime timestamp) {
        this.sender = sender;
        this.receiver = receiver;
        this.amount = amount;
        this.timestamp = timestamp;
    }

    private UserAccountModel sender;

    private UserAccountModel receiver;

    private BigDecimal amount;

    private LocalDateTime timestamp;

    private String createdBy;

    private String updatedBy;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}
