package com.hendi.banktransfersystem.infrastructure.transaction.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hendi.banktransfersystem.entity.transaction.exception.InsufficientBalanceException;
import com.hendi.banktransfersystem.entity.transaction.exception.TransactionNotFoundException;
import com.hendi.banktransfersystem.entity.transaction.model.TransactionModel;
import com.hendi.banktransfersystem.entity.user.exception.UserNotFoundException;
import com.hendi.banktransfersystem.infrastructure.config.web.response.WebHttpResponse;
import com.hendi.banktransfersystem.infrastructure.transaction.dto.TransactionPublicData;
import com.hendi.banktransfersystem.infrastructure.transaction.dto.TransactionTransferData;
import com.hendi.banktransfersystem.usecase.transaction.GetTransactionUseCase;
import com.hendi.banktransfersystem.usecase.transaction.TransactionTransferUseCase;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private final GetTransactionUseCase getTransactionUseCase;
    private final TransactionTransferUseCase transactionTransferUseCase;

    public TransactionController(GetTransactionUseCase getTransactionUseCase,
            TransactionTransferUseCase transactionTransferUseCase) {
        this.getTransactionUseCase = getTransactionUseCase;
        this.transactionTransferUseCase = transactionTransferUseCase;
    }

    @GetMapping("/{id}")
    public ResponseEntity<WebHttpResponse<TransactionPublicData>> getTransactionById(@PathVariable Long id)
            throws TransactionNotFoundException {
        TransactionModel transactionData = getTransactionUseCase.execute(id);
        return new ResponseEntity<>(WebHttpResponse.ok(new TransactionPublicData(transactionData)), HttpStatus.OK);
    }

    @PostMapping("/{senderId}/transfer")
    public synchronized ResponseEntity<WebHttpResponse<TransactionPublicData>> transfer(@PathVariable Long senderId,
            @Valid @RequestBody TransactionTransferData request)
            throws UserNotFoundException, InsufficientBalanceException {
        TransactionModel transactionData = transactionTransferUseCase.execute(senderId, request);
        return new ResponseEntity<>(WebHttpResponse.ok(new TransactionPublicData(transactionData)), HttpStatus.OK);
    }

}
