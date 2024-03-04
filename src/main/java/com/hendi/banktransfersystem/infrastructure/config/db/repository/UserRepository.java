package com.hendi.banktransfersystem.infrastructure.config.db.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.hendi.banktransfersystem.infrastructure.config.db.schema.UserSchema;

public interface UserRepository extends JpaRepository<UserSchema, Long> {
    // Add custom query methods if needed

    Optional<UserSchema> findByUsername(String username);
}