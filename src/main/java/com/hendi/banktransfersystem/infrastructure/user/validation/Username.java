package com.hendi.banktransfersystem.infrastructure.user.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;

@Constraint(validatedBy = UsernameValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Username {

    String message() default "Username must consist of alphanumeric characters, underscores, and dots only.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
