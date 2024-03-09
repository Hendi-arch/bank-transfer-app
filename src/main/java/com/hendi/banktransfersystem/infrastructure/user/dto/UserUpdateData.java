package com.hendi.banktransfersystem.infrastructure.user.dto;

import java.math.BigDecimal;

import com.hendi.banktransfersystem.infrastructure.shared.validation.Amount;
import com.hendi.banktransfersystem.infrastructure.user.validation.Password;
import com.hendi.banktransfersystem.usecase.user.dto.IUserUpdateData;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserUpdateData(
        @NotBlank @Password String password,
        @Amount BigDecimal balance,
        @NotNull Long roleId) implements IUserUpdateData {
}
