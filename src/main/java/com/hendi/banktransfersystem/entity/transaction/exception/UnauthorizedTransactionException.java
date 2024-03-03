package com.hendi.banktransfersystem.entity.transaction.exception;

public class UnauthorizedTransactionException extends RuntimeException {

    public UnauthorizedTransactionException() {
        super("Unauthorized transaction");
    }
}
