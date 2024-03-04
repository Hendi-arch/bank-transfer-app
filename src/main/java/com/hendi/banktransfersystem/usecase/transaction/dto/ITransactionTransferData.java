package com.hendi.banktransfersystem.usecase.transaction.dto;

import java.math.BigDecimal;

public interface ITransactionTransferData {

    Long receiver();

    BigDecimal amount();

}
