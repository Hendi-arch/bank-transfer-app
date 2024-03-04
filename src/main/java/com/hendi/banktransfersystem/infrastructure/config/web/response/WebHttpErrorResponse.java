package com.hendi.banktransfersystem.infrastructure.config.web.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class WebHttpErrorResponse {
    private String field;
    private String message;
}
