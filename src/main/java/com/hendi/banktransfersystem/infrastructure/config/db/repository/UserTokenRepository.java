package com.hendi.banktransfersystem.infrastructure.config.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hendi.banktransfersystem.infrastructure.config.db.schema.UserTokenSchema;

public interface UserTokenRepository extends JpaRepository<UserTokenSchema, Long> {
    // Add custom query methods if needed
}