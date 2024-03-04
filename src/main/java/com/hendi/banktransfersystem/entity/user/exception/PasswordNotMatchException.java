package com.hendi.banktransfersystem.entity.user.exception;

public class PasswordNotMatchException extends RuntimeException {

    public PasswordNotMatchException() {
        super("Passwords do not match.");
    }

}
