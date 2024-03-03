package com.hendi.banktransfersystem.entity.transaction.exception;

public class InvalidTransactionAmountException extends RuntimeException {

    public InvalidTransactionAmountException() {
        super("Invalid transaction amount");
    }
}
