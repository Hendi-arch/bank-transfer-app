package com.hendi.banktransfersystem.usecase.transaction.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.hendi.banktransfersystem.usecase.user.dto.IUserPublicData;

public interface ITransactionPublicData {

    Long id();
    
    IUserPublicData sender();

    IUserPublicData receiver();

    BigDecimal amount();

    LocalDateTime timestamp();

}
