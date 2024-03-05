package com.hendi.banktransfersystem.infrastructure.user.dto;

import com.hendi.banktransfersystem.infrastructure.user.validation.Password;
import com.hendi.banktransfersystem.infrastructure.user.validation.Username;
import com.hendi.banktransfersystem.usecase.user.dto.IUserLoginData;

import jakarta.validation.constraints.NotBlank;

public record UserLoginData(
        @NotBlank @Username String username,
        @NotBlank @Password String password) implements IUserLoginData {
}
