package com.hendi.banktransfersystem.infrastructure.config.web.response;

public record WebHttpErrorResponse(
        String field,
        String message) {
}
