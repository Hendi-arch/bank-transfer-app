package com.hendi.banktransfersystem.infrastructure.user.dto;

import java.math.BigDecimal;

import com.hendi.banktransfersystem.infrastructure.user.validation.Balance;
import com.hendi.banktransfersystem.infrastructure.user.validation.Password;
import com.hendi.banktransfersystem.infrastructure.user.validation.Username;
import com.hendi.banktransfersystem.usecase.user.dto.IUserCreateData;

import jakarta.validation.constraints.NotBlank;

public record UserCreateData(
        @NotBlank @Username String username,
        @NotBlank @Password String password,
        @Balance BigDecimal balance) implements IUserCreateData {
}
