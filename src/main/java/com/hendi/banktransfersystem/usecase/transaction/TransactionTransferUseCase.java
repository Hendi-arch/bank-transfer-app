package com.hendi.banktransfersystem.usecase.transaction;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.hendi.banktransfersystem.entity.transaction.exception.InsufficientBalanceException;
import com.hendi.banktransfersystem.entity.transaction.gateway.TransactionGateway;
import com.hendi.banktransfersystem.entity.transaction.model.TransactionModel;
import com.hendi.banktransfersystem.entity.user.exception.UserNotFoundException;
import com.hendi.banktransfersystem.entity.user.gateway.UserGateway;
import com.hendi.banktransfersystem.entity.user.model.UserAccountModel;
import com.hendi.banktransfersystem.usecase.transaction.dto.ITransactionTransferData;

public class TransactionTransferUseCase {

    private final TransactionGateway transactionGateway;
    private final UserGateway userGateway;

    public TransactionTransferUseCase(TransactionGateway transactionGateway, UserGateway userGateway) {
        this.transactionGateway = transactionGateway;
        this.userGateway = userGateway;
    }

    public TransactionModel execute(Long senderId, ITransactionTransferData data)
            throws UserNotFoundException, InsufficientBalanceException {
        UserAccountModel sender = userGateway.findById(senderId).orElseThrow(UserNotFoundException::new);
        boolean hasSufficientBalanceForTransaction = userGateway.hasSufficientBalanceForTransaction(sender.getId(),
                data.amount());
        if (!hasSufficientBalanceForTransaction) {
            throw new InsufficientBalanceException();
        }

        UserAccountModel receiver = userGateway.findById(data.receiver()).orElseThrow(UserNotFoundException::new);
        BigDecimal amount = data.amount();
        LocalDateTime timestamp = LocalDateTime.now();

        TransactionModel transaction = new TransactionModel(sender, receiver, amount, timestamp);
        return transactionGateway.transfer(transaction);
    }

}
