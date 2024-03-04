package com.hendi.banktransfersystem.usecase.transaction;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.hendi.banktransfersystem.entity.transaction.gateway.TransactionGateway;
import com.hendi.banktransfersystem.entity.transaction.model.TransactionModel;
import com.hendi.banktransfersystem.entity.user.model.UserAccountModel;
import com.hendi.banktransfersystem.usecase.transaction.dto.ITransactionTransferData;

public class TransactionTransferUseCase {

    private final TransactionGateway transactionGateway;

    public TransactionTransferUseCase(TransactionGateway transactionGateway) {
        this.transactionGateway = transactionGateway;
    }

    public TransactionModel execute(ITransactionTransferData data) {
        UserAccountModel sender = data.sender();
        UserAccountModel receiver = data.receiver();
        BigDecimal amount = data.amount();
        LocalDateTime timestamp = data.timestamp();

        TransactionModel transaction = new TransactionModel(sender, receiver, amount, timestamp);
        return transactionGateway.transfer(transaction);
    }

}
