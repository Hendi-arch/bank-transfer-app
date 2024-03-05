package com.hendi.banktransfersystem.infrastructure.shared.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.math.BigDecimal;

public class AmountValidator implements ConstraintValidator<Amount, BigDecimal> {

    @Override
    public boolean isValid(BigDecimal amount, ConstraintValidatorContext context) {
        // Allow null values
        if (amount == null) {
            return true;
        }
        // Validate non-negative values
        return amount.compareTo(BigDecimal.ZERO) >= 0;
    }

}
