package com.hendi.banktransfersystem.infrastructure.user.dto;

import com.hendi.banktransfersystem.infrastructure.user.validation.Password;
import com.hendi.banktransfersystem.usecase.user.dto.IUserUpdateData;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserUpdateData(
        @NotBlank @Password String password,
        @NotNull Long roleId) implements IUserUpdateData {
}
