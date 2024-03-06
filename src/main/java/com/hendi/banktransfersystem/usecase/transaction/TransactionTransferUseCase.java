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

import jakarta.transaction.Transactional;

public class TransactionTransferUseCase {

    private final TransactionGateway transactionGateway;
    private final UserGateway userGateway;

    public TransactionTransferUseCase(TransactionGateway transactionGateway, UserGateway userGateway) {
        this.transactionGateway = transactionGateway;
        this.userGateway = userGateway;
    }

    @Transactional
    public TransactionModel execute(Long senderId, ITransactionTransferData data)
            throws UserNotFoundException, InsufficientBalanceException {
        UserAccountModel sender = userGateway.findById(senderId).orElseThrow(UserNotFoundException::new);

        BigDecimal amount = data.amount();
        if (!userGateway.hasSufficientBalanceForTransaction(sender.getId(), amount)) {
            throw new InsufficientBalanceException();
        }

        BigDecimal newSenderBalance = sender.getBalance().subtract(amount);
        sender.setBalance(newSenderBalance);
        userGateway.update(sender);

        UserAccountModel receiver = userGateway.findById(data.receiver()).orElseThrow(UserNotFoundException::new);
        BigDecimal newReceiverBalance = receiver.getBalance().add(amount);
        receiver.setBalance(newReceiverBalance);
        userGateway.update(receiver);

        LocalDateTime timestamp = LocalDateTime.now();
        TransactionModel transaction = new TransactionModel(sender, receiver, amount, timestamp);
        return transactionGateway.transfer(transaction);
    }

}
