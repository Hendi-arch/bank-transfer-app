package com.hendi.banktransfersystem.entity.transaction.exception;

public class InsufficientBalanceException extends RuntimeException {

    public InsufficientBalanceException() {
        super("Insufficient balance for the transaction");
    }
}
