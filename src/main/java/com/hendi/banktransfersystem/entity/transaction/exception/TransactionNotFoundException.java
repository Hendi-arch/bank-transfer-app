package com.hendi.banktransfersystem.entity.transaction.exception;

public class TransactionNotFoundException extends RuntimeException {

    public TransactionNotFoundException() {
        super("Transaction not found");
    }
}
