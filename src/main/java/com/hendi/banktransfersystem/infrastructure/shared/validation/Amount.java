package com.hendi.banktransfersystem.infrastructure.shared.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;

@Constraint(validatedBy = AmountValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Amount {

    String message() default "Amount must be non-negative.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
