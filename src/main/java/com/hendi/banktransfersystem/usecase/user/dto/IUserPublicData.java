package com.hendi.banktransfersystem.usecase.user.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.hendi.banktransfersystem.usecase.userrole.dto.IUserRolePublicData;

public interface IUserPublicData {

    Long id();

    String username();

    BigDecimal balance();

    IUserRolePublicData role();

    LocalDateTime createdAt();

    LocalDateTime updatedAt();

}
