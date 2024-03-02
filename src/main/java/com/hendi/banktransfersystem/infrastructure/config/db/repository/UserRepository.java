package com.hendi.banktransfersystem.infrastructure.config.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hendi.banktransfersystem.infrastructure.config.db.schema.UserSchema;

public interface UserRepository extends JpaRepository<UserSchema, Long> {
    // Add custom query methods if needed
}