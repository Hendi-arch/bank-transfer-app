package com.hendi.banktransfersystem.infrastructure.user.validation;

import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

@Component
public class UsernameValidator implements ConstraintValidator<Username, String> {

    @Override
    public boolean isValid(String username, ConstraintValidatorContext context) {
        // Regular expression for username format validation
        // Assuming username consists of alphanumeric characters, underscores, and dots
        String regex = "^[a-zA-Z0-9_\\.]+$";

        // Compile the regular expression
        Pattern pattern = Pattern.compile(regex);

        // Match the username against the regular expression
        Matcher matcher = pattern.matcher(username);

        // Return true if the username contains a valid format, false otherwise
        return matcher.matches();
    }

}
