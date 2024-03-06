package com.hendi.banktransfersystem.infrastructure.config.web.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Data;

@Data
@JsonInclude(value = Include.NON_NULL)
public class WebHttpResponse<T> {
    private Integer status;
    private String message;
    private T data;

    public String toJson() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(this);
    }

    public static <T> WebHttpResponse<T> of(Integer status, String message, T data) {
        WebHttpResponse<T> response = new WebHttpResponse<>();
        response.setStatus(status);
        response.setMessage(message);
        response.setData(data);
        return response;
    }

    public static <T> WebHttpResponse<T> ok(T data) {
        return of(200, "Ok", data);
    }

    public static <T> WebHttpResponse<T> created(T data) {
        return of(201, "Created", data);
    }

    public static <T> WebHttpResponse<T> badRequest(T data) {
        return of(400, "Bad Request", data);
    }

    public static <T> WebHttpResponse<T> notFound(T data) {
        return of(404, "Not Found", data);
    }

    public static <T> WebHttpResponse<T> internalServerError(T data) {
        return of(500, "Internal Server Error", data);
    }

    public static <T> WebHttpResponse<T> unauthorized(T data) {
        return of(401, "Unauthorized", data);
    }

    public static <T> WebHttpResponse<T> forbidden(T data) {
        return of(403, "Forbidden", data);
    }
}
