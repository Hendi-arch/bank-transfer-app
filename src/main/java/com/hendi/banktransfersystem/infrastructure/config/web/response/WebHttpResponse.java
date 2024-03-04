package com.hendi.banktransfersystem.infrastructure.config.web.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonInclude(value = Include.NON_NULL)
public class WebHttpResponse<T> {
    private Integer status;
    private String message;
    private List<WebHttpErrorResponse> errors;
    private String auth;
    private T data;

    public static <T> WebHttpResponse<T> ok(T data) {
        return of(200, "Ok", data);
    }

    public static <T> WebHttpResponse<T> created(T data) {
        return of(201, "Created", data);
    }

    public static <T> WebHttpResponse<T> of(Integer status, String message, T data) {
        WebHttpResponse<T> response = new WebHttpResponse<>();
        response.setStatus(status);
        response.setMessage(message);
        response.setData(data);
        return response;
    }

    public static <T> WebHttpResponse<T> of(Integer status, String message, String auth, T data) {
        WebHttpResponse<T> response = new WebHttpResponse<>();
        response.setStatus(status);
        response.setMessage(message);
        response.setAuth(auth);
        response.setData(data);
        return response;
    }

    public static <T> WebHttpResponse<T> of(Integer status, String message, List<WebHttpErrorResponse> errors) {
        WebHttpResponse<T> response = new WebHttpResponse<>();
        response.setStatus(status);
        response.setMessage(message);
        response.setErrors(errors);
        return response;
    }
}
