package com.hendi.banktransfersystem.infrastructure.usertoken.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hendi.banktransfersystem.entity.usertoken.model.UserTokenModel;
import com.hendi.banktransfersystem.infrastructure.config.web.response.WebHttpResponse;
import com.hendi.banktransfersystem.infrastructure.usertoken.dto.UserTokenCreateData;
import com.hendi.banktransfersystem.infrastructure.usertoken.dto.UserTokenPublicData;
import com.hendi.banktransfersystem.usecase.usertoken.CreateUserTokenUseCase;
import com.hendi.banktransfersystem.usecase.usertoken.DeleteUserTokenUseCase;
import com.hendi.banktransfersystem.usecase.usertoken.GetUserTokenUseCase;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/usertokens")
public class UserTokenController {

    private final GetUserTokenUseCase getUserTokenUseCase;
    private final CreateUserTokenUseCase createUserTokenUseCase;
    private final DeleteUserTokenUseCase deleteUserTokenUseCase;

    public UserTokenController(
            GetUserTokenUseCase getUserTokenUseCase,
            CreateUserTokenUseCase createUserTokenUseCase,
            DeleteUserTokenUseCase deleteUserTokenUseCase) {
        this.getUserTokenUseCase = getUserTokenUseCase;
        this.createUserTokenUseCase = createUserTokenUseCase;
        this.deleteUserTokenUseCase = deleteUserTokenUseCase;
    }

    @GetMapping("/{authToken}")
    public ResponseEntity<WebHttpResponse<UserTokenPublicData>> getUserToken(@PathVariable String authToken) {
        UserTokenModel userTokenData = getUserTokenUseCase.execute(authToken);
        return new ResponseEntity<>(WebHttpResponse.ok(new UserTokenPublicData(userTokenData)), HttpStatus.OK);
    }

    @DeleteMapping("/{authToken}")
    public ResponseEntity<WebHttpResponse<String>> deleteUserToken(@PathVariable String authToken) {
        deleteUserTokenUseCase.execute(authToken);
        return new ResponseEntity<>(WebHttpResponse.ok("Success"), HttpStatus.OK);
    }

    @PostMapping("/usertoken")
    public ResponseEntity<WebHttpResponse<UserTokenPublicData>> createUserToken(
            @Valid @RequestBody UserTokenCreateData request) {
        UserTokenModel userTokenData = createUserTokenUseCase.execute(request);
        return new ResponseEntity<>(WebHttpResponse.created(new UserTokenPublicData(userTokenData)), HttpStatus.CREATED);
    }

}
