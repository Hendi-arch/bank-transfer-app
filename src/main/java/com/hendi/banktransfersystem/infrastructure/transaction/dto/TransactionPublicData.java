package com.hendi.banktransfersystem.infrastructure.transaction.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.hendi.banktransfersystem.entity.transaction.model.TransactionModel;
import com.hendi.banktransfersystem.entity.user.model.UserAccountModel;
import com.hendi.banktransfersystem.infrastructure.user.dto.UserPublicData;
import com.hendi.banktransfersystem.usecase.transaction.dto.ITransactionPublicData;

public record TransactionPublicData(
        Long id,
        UserPublicData sender,
        UserPublicData receiver,
        BigDecimal amount,
        LocalDateTime timestamp) implements ITransactionPublicData {

    public TransactionPublicData(TransactionModel data) {
        this(data.getId(), mapUser(data.getSender()), mapUser(data.getReceiver()), data.getAmount(),
                data.getTimestamp());
    }

    private static UserPublicData mapUser(UserAccountModel data) {
        return new UserPublicData(data);
    }
}
