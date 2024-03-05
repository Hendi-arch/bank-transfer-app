package com.hendi.banktransfersystem.infrastructure.transaction.gateway;

import java.util.Optional;

import com.hendi.banktransfersystem.entity.transaction.gateway.TransactionGateway;
import com.hendi.banktransfersystem.entity.transaction.model.TransactionModel;
import com.hendi.banktransfersystem.infrastructure.config.db.repository.TransactionRepository;
import com.hendi.banktransfersystem.infrastructure.config.db.schema.TransactionSchema;

public class TransactionDatabaseGateway implements TransactionGateway {

    private final TransactionRepository repository;

    public TransactionDatabaseGateway(TransactionRepository repository) {
        this.repository = repository;
    }

    @Override
    public TransactionModel transfer(TransactionModel transactionModel) {
        return repository.save(new TransactionSchema(transactionModel)).toTransactionModel();
    }

    @Override
    public Optional<TransactionModel> findById(Long id) {
        return repository.findById(id).map(TransactionSchema::toTransactionModel);
    }

}
