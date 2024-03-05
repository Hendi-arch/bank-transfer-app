package com.hendi.banktransfersystem.entity.transaction.gateway;

import java.util.Optional;

import com.hendi.banktransfersystem.entity.transaction.model.TransactionModel;

public interface TransactionGateway {

    TransactionModel transfer(TransactionModel transactionModel);

    Optional<TransactionModel> findById(Long id);

}
