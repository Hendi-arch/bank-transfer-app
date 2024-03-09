package com.hendi.banktransfersystem.infrastructure.userrole.gateway;

import java.util.List;
import java.util.Optional;

import com.hendi.banktransfersystem.entity.userrole.gateway.UserRoleGateway;
import com.hendi.banktransfersystem.entity.userrole.model.UserRoleModel;
import com.hendi.banktransfersystem.infrastructure.config.db.repository.UserRoleRepository;
import com.hendi.banktransfersystem.infrastructure.config.db.schema.UserRoleSchema;

public class UserRoleDatabaseGateway implements UserRoleGateway {

    private final UserRoleRepository repository;

    public UserRoleDatabaseGateway(UserRoleRepository userRoleRepository) {
        this.repository = userRoleRepository;
    }

    @Override
    public UserRoleModel create(UserRoleModel userRoleModel) {
        return repository.save(new UserRoleSchema(userRoleModel)).toUserRoleModel();
    }

    @Override
    public Optional<UserRoleModel> findById(Long id) {
        return repository.findById(id).map(UserRoleSchema::toUserRoleModel);
    }

    @Override
    public List<UserRoleModel> findAll() {
        return repository.findAll().stream().map(UserRoleSchema::toUserRoleModel).toList();
    }

    @Override
    public List<UserRoleModel> createAll(List<UserRoleModel> userRoleModels) {
        return repository.saveAll(UserRoleSchema.toUserRoleSchemaList(userRoleModels)).stream()
                .map(UserRoleSchema::toUserRoleModel).toList();
    }

}
