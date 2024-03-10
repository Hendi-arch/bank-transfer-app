package com.hendi.banktransfersystem.infrastructure.user.controller;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.TestPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.hendi.banktransfersystem.infrastructure.config.db.schema.UserRoleSchema.RoleEnum;
import com.hendi.banktransfersystem.infrastructure.config.web.response.WebHttpResponse;
import com.hendi.banktransfersystem.infrastructure.shared.TestUtils;
import com.hendi.banktransfersystem.infrastructure.user.dto.UserCreateData;
import com.hendi.banktransfersystem.infrastructure.user.dto.UserLoginData;
import com.hendi.banktransfersystem.infrastructure.user.dto.UserPublicData;
import com.hendi.banktransfersystem.infrastructure.user.dto.UserUpdateData;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties = { "spring.profiles.active=dev" })
@Testcontainers
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserControllerTest {

    @Container
    @ServiceConnection
    private static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:15-alpine");

    @DynamicPropertySource
    private static void setProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
    }

    @Autowired
    private TestRestTemplate restTemplate;

    private String baseUrl;

    @LocalServerPort
    private int serverPort;

    private static final String USERNAME = "hendi_novi";
    private static final String PASSWORD = "HENdi123$$##";
    private static String jwtToken;
    private static Long userId;

    @BeforeEach
    void beforeEach() {
        baseUrl = "http://localhost:" + serverPort + "/users";
    }

    @Test
    @Order(1)
    void testUserCreationSuccess() throws JsonProcessingException {
        UserCreateData createData = new UserCreateData(USERNAME, PASSWORD, BigDecimal.ZERO, 1L);
        ResponseEntity<WebHttpResponse<UserPublicData>> responseEntity = restTemplate.exchange(
                baseUrl + "/user", HttpMethod.POST, new HttpEntity<>(createData), TestUtils.webHttpResponseType());

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());

        WebHttpResponse<UserPublicData> responseBody = TestUtils.deserializeResponseBody(responseEntity.getBody(),
                new TypeReference<WebHttpResponse<UserPublicData>>() {
                });
        assertNotNull(responseBody.getData());

        UserPublicData publicData = responseBody.getData();
        assertEquals(1L, publicData.id());
        assertEquals(USERNAME, publicData.username());
        assertEquals(BigDecimal.ZERO, publicData.balance());
        assertNotNull(publicData.createdAt());
        assertNotNull(publicData.updatedAt());

        assertNotNull(publicData.role());
        assertEquals(RoleEnum.SUPER_ADMIN.name(), publicData.role().role());
    }

    @Test
    @Order(2)
    void testLoginUserSuccess() throws JsonProcessingException {
        UserLoginData loginData = new UserLoginData(USERNAME, PASSWORD);
        ResponseEntity<WebHttpResponse<UserPublicData>> responseEntity = restTemplate.exchange(
                baseUrl + "/login", HttpMethod.POST, new HttpEntity<>(loginData), TestUtils.webHttpResponseType());

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());

        WebHttpResponse<UserPublicData> responseBody = TestUtils.deserializeResponseBody(responseEntity.getBody(),
                new TypeReference<WebHttpResponse<UserPublicData>>() {
                });
        assertNotNull(responseBody.getData());

        UserPublicData publicData = responseBody.getData();
        assertEquals(1L, publicData.id());
        userId = publicData.id();

        assertEquals(USERNAME, publicData.username());
        assertEquals(BigDecimal.valueOf(0.0), publicData.balance());
        assertNotNull(publicData.createdAt());
        assertNotNull(publicData.updatedAt());

        assertNotNull(publicData.jwtExpiryDateTime());
        assertNotNull(publicData.jwtToken());
        jwtToken = publicData.jwtToken();

        assertNotNull(publicData.role());
        assertEquals(RoleEnum.SUPER_ADMIN.name(), publicData.role().role());
    }

    @Test
    @Order(3)
    void testGetAllUsers() throws JsonProcessingException {
        HttpHeaders httpHeaders = TestUtils.getAuthHeaders(jwtToken);
        ResponseEntity<WebHttpResponse<List<UserPublicData>>> responseEntity = restTemplate.exchange(
                baseUrl + "/all", HttpMethod.GET, new HttpEntity<>(httpHeaders), TestUtils.webHttpResponseType());

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());

        WebHttpResponse<List<UserPublicData>> responseBody = TestUtils.deserializeResponseBody(responseEntity.getBody(),
                new TypeReference<WebHttpResponse<List<UserPublicData>>>() {
                });
        assertNotNull(responseBody.getData());
        assertFalse(responseBody.getData().isEmpty());
    }

    @Test
    @Order(4)
    void testGetUserById() throws JsonProcessingException {
        HttpHeaders httpHeaders = TestUtils.getAuthHeaders(jwtToken);
        ResponseEntity<WebHttpResponse<UserPublicData>> responseEntity = restTemplate.exchange(
                baseUrl + "/{id}", HttpMethod.GET, new HttpEntity<>(httpHeaders), TestUtils.webHttpResponseType(),
                userId);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());

        WebHttpResponse<UserPublicData> responseBody = TestUtils.deserializeResponseBody(responseEntity.getBody(),
                new TypeReference<WebHttpResponse<UserPublicData>>() {
                });
        assertNotNull(responseBody.getData());

        UserPublicData publicData = responseBody.getData();
        assertEquals(userId, publicData.id());
        assertEquals(USERNAME, publicData.username());
        assertEquals(BigDecimal.valueOf(0.0), publicData.balance());

        assertNotNull(publicData.role());
        assertEquals(RoleEnum.SUPER_ADMIN.name(), publicData.role().role());
    }

    @Test
    @Order(5)
    void testUpdateUser() throws JsonProcessingException {
        UserUpdateData updateData = new UserUpdateData(PASSWORD, BigDecimal.valueOf(10_000_000L), 1L);
        HttpHeaders httpHeaders = TestUtils.getAuthHeaders(jwtToken);
        ResponseEntity<WebHttpResponse<UserPublicData>> responseEntity = restTemplate.exchange(
                baseUrl + "/{id}", HttpMethod.PUT, new HttpEntity<>(updateData, httpHeaders),
                TestUtils.webHttpResponseType(), userId);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());

        WebHttpResponse<UserPublicData> responseBody = TestUtils.deserializeResponseBody(responseEntity.getBody(),
                new TypeReference<WebHttpResponse<UserPublicData>>() {
                });
        assertNotNull(responseBody.getData());

        UserPublicData publicData = responseBody.getData();
        assertEquals(userId, publicData.id());
        assertEquals(USERNAME, publicData.username());
        assertEquals(BigDecimal.valueOf(10_000_000L), publicData.balance());
        assertNotNull(publicData.createdAt());
        assertNotNull(publicData.updatedAt());

        assertNotNull(publicData.role());
        assertEquals(RoleEnum.SUPER_ADMIN.name(), publicData.role().role());
    }

}
