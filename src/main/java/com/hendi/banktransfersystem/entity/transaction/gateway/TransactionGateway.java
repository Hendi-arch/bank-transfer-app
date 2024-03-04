package com.hendi.banktransfersystem.entity.transaction.gateway;

import com.hendi.banktransfersystem.entity.transaction.exception.InsufficientBalanceException;
import com.hendi.banktransfersystem.entity.transaction.exception.InvalidTransactionAmountException;
import com.hendi.banktransfersystem.entity.transaction.exception.TransactionNotFoundException;
import com.hendi.banktransfersystem.entity.transaction.exception.UnauthorizedTransactionException;
import com.hendi.banktransfersystem.entity.transaction.model.TransactionModel;

public interface TransactionGateway {

    TransactionModel transfer(TransactionModel transactionModel)
            throws InvalidTransactionAmountException, InsufficientBalanceException,
            TransactionNotFoundException, UnauthorizedTransactionException;

    TransactionModel getTransactionById(Long id) throws TransactionNotFoundException;

}
