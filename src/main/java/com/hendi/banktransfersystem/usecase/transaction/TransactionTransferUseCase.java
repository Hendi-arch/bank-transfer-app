package com.hendi.banktransfersystem.usecase.transaction;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.hendi.banktransfersystem.entity.transaction.exception.InsufficientBalanceException;
import com.hendi.banktransfersystem.entity.transaction.gateway.TransactionGateway;
import com.hendi.banktransfersystem.entity.transaction.model.TransactionModel;
import com.hendi.banktransfersystem.entity.user.exception.UserNotFoundException;
import com.hendi.banktransfersystem.entity.user.model.UserAccountModel;
import com.hendi.banktransfersystem.usecase.transaction.dto.ITransactionTransferData;
import com.hendi.banktransfersystem.usecase.user.GetUserUseCase;

public class TransactionTransferUseCase {

    private final TransactionGateway transactionGateway;
    private final GetUserUseCase getUserUseCase;

    public TransactionTransferUseCase(TransactionGateway transactionGateway, GetUserUseCase getUserUseCase) {
        this.transactionGateway = transactionGateway;
        this.getUserUseCase = getUserUseCase;
    }

    public TransactionModel execute(Long senderId, ITransactionTransferData data)
            throws UserNotFoundException, InsufficientBalanceException {
        UserAccountModel sender = getUserUseCase.findById(senderId);
        getUserUseCase.verifySufficientBalanceForTransaction(sender.getId(), data.amount());

        UserAccountModel receiver = getUserUseCase.findById(data.receiver());
        BigDecimal amount = data.amount();
        LocalDateTime timestamp = LocalDateTime.now();

        TransactionModel transaction = new TransactionModel(sender, receiver, amount, timestamp);
        return transactionGateway.transfer(transaction);
    }

}
