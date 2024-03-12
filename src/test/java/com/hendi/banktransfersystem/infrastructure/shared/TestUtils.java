package com.hendi.banktransfersystem.infrastructure.shared;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class TestUtils {

    private static final ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());

    public static <T> T deserializeResponseBody(Object responseBody, TypeReference<T> typeReference)
            throws JsonProcessingException {
        if (responseBody == null) {
            throw new IllegalArgumentException("Response body is null");
        }
        return objectMapper.convertValue(responseBody, typeReference);
    }

    public static <T> ParameterizedTypeReference<T> webHttpResponseType() {
        return new ParameterizedTypeReference<T>() {
        };
    }

    public static HttpHeaders getAuthHeaders(String jwtToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(jwtToken);
        return headers;
    }

    public static String getBaseUrl(int serverPort, String requestMapping, String url) {
        return "http://localhost:" + serverPort + requestMapping + url;
    }

    public static String getUsersBaseUrl(int serverPort, String url) {
        return getBaseUrl(serverPort, "/users", url);
    }

    public static String getUserRolesBaseUrl(int serverPort, String url) {
        return getBaseUrl(serverPort, "/userroles", url);
    }

    public static String getUserTokensBaseUrl(int serverPort, String url) {
        return getBaseUrl(serverPort, "/usertokens", url);
    }

    public static String getTransactionsBaseUrl(int serverPort, String url) {
        return getBaseUrl(serverPort, "/transactions", url);
    }

}
