package com.hendi.banktransfersystem.infrastructure.config.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hendi.banktransfersystem.infrastructure.config.db.schema.TransactionSchema;

public interface TransactionRepository extends JpaRepository<TransactionSchema, Long> {
    // Add custom query methods if needed
}
