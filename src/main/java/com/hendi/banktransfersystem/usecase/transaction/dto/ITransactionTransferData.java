package com.hendi.banktransfersystem.usecase.transaction.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.hendi.banktransfersystem.entity.user.model.UserAccountModel;

public interface ITransactionTransferData {

    UserAccountModel sender();

    UserAccountModel receiver();

    BigDecimal amount();

    LocalDateTime timestamp();

}
