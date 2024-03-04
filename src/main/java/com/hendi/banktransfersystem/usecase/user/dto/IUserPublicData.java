package com.hendi.banktransfersystem.usecase.user.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface IUserPublicData {

    Long id();

    String username();

    BigDecimal balance();

    LocalDateTime createdAt();

    LocalDateTime updatedAt();

}
