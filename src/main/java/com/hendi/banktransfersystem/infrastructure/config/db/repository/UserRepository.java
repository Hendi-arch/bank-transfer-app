package com.hendi.banktransfersystem.infrastructure.config.db.repository;

import java.math.BigDecimal;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hendi.banktransfersystem.infrastructure.config.db.schema.UserSchema;

public interface UserRepository extends JpaRepository<UserSchema, Long> {
    // Add custom query methods if needed

    Optional<UserSchema> findByUsername(String username);

    @Query("SELECT CASE WHEN u.balance >= :transactionAmount THEN true ELSE false END " +
            "FROM UserSchema u WHERE u.id = :senderId")
    boolean hasSufficientBalanceForTransaction(Long senderId, BigDecimal transactionAmount);
}