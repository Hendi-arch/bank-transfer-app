package com.hendi.banktransfersystem.infrastructure.user.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;

@Constraint(validatedBy = BalanceValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Balance {

    String message() default "Balance must be non-negative.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
