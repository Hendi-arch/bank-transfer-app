package com.hendi.banktransfersystem.entity.user.exception;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException() {
        super("User not found in the system");
    }

}
