package com.hendi.banktransfersystem.infrastructure.user.gateway;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import com.hendi.banktransfersystem.entity.user.gateway.UserGateway;
import com.hendi.banktransfersystem.entity.user.model.UserAccountModel;
import com.hendi.banktransfersystem.infrastructure.config.db.repository.UserRepository;
import com.hendi.banktransfersystem.infrastructure.config.db.schema.UserSchema;

public class UserDatabaseGateway implements UserGateway {

    private final UserRepository repository;

    public UserDatabaseGateway(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserAccountModel create(UserAccountModel userAccountModel) {
        return repository.save(new UserSchema(userAccountModel)).toUserAccountModel();
    }

    @Override
    public UserAccountModel update(UserAccountModel userAccountModel) {
        return repository.save(new UserSchema(userAccountModel)).toUserAccountModel();
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<UserAccountModel> findById(Long id) {
        return repository.findById(id).map(UserSchema::toUserAccountModel);
    }

    @Override
    public Optional<UserAccountModel> findByUsername(String username) {
        return repository.findByUsername(username).map(UserSchema::toUserAccountModel);
    }

    @Override
    public List<UserAccountModel> findAll() {
        return repository.findAll().stream().map(UserSchema::toUserAccountModel).toList();
    }

    @Override
    public boolean hasSufficientBalanceForTransaction(Long senderId, BigDecimal transactionAmount) {
        return repository.hasSufficientBalanceForTransaction(senderId, transactionAmount);
    }

}
