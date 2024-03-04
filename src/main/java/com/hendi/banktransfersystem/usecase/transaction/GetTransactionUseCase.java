package com.hendi.banktransfersystem.usecase.transaction;

import com.hendi.banktransfersystem.entity.transaction.gateway.TransactionGateway;
import com.hendi.banktransfersystem.entity.transaction.model.TransactionModel;

public class GetTransactionUseCase {
    
    private final TransactionGateway transactionGateway;

    public GetTransactionUseCase(TransactionGateway transactionGateway) {
        this.transactionGateway = transactionGateway;
    }

    public TransactionModel execute(Long id) {
        return transactionGateway.getTransactionById(id);
    }

}
