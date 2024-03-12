package com.hendi.banktransfersystem.infrastructure.user.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<Password, String> {

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        // Regular expression for password format validation
        String regex = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[!@#$%^&*()-+=]).{8,}$";

        // Return true if the password matches the regular expression, false otherwise
        return password.matches(regex);
    }

}
