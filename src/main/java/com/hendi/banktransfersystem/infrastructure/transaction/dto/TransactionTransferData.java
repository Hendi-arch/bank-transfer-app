package com.hendi.banktransfersystem.infrastructure.transaction.dto;

import java.math.BigDecimal;

import com.hendi.banktransfersystem.infrastructure.shared.validation.Amount;
import com.hendi.banktransfersystem.usecase.transaction.dto.ITransactionTransferData;

import jakarta.validation.constraints.NotNull;

public record TransactionTransferData(
        @NotNull Long receiver,
        @NotNull @Amount BigDecimal amount) implements ITransactionTransferData {
}
