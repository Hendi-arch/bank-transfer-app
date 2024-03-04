package com.hendi.banktransfersystem.infrastructure.user.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.math.BigDecimal;

public class BalanceValidator implements ConstraintValidator<Balance, BigDecimal> {

    @Override
    public boolean isValid(BigDecimal balance, ConstraintValidatorContext context) {
        // Allow null values
        if (balance == null) {
            return true;
        }
        // Validate non-negative values
        return balance.compareTo(BigDecimal.ZERO) >= 0;
    }

}
