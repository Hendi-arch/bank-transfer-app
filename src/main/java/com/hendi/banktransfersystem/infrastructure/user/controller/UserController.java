package com.hendi.banktransfersystem.infrastructure.user.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import com.hendi.banktransfersystem.entity.user.exception.PasswordNotMatchException;
import com.hendi.banktransfersystem.entity.user.exception.UserNotFoundException;
import com.hendi.banktransfersystem.entity.user.model.UserAccountModel;
import com.hendi.banktransfersystem.infrastructure.config.web.response.WebHttpResponse;
import com.hendi.banktransfersystem.infrastructure.user.dto.UserCreateData;
import com.hendi.banktransfersystem.infrastructure.user.dto.UserLoginData;
import com.hendi.banktransfersystem.infrastructure.user.dto.UserPublicData;
import com.hendi.banktransfersystem.infrastructure.user.dto.UserUpdateData;
import com.hendi.banktransfersystem.usecase.user.CreateUserUseCase;
import com.hendi.banktransfersystem.usecase.user.GetUserUseCase;
import com.hendi.banktransfersystem.usecase.user.LoginUserUseCase;
import com.hendi.banktransfersystem.usecase.user.SearchUserUseCase;
import com.hendi.banktransfersystem.usecase.user.UpdateUserUseCase;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/users")
public class UserController {

    private final LoginUserUseCase loginUserUseCase;
    private final CreateUserUseCase createUserUseCase;
    private final GetUserUseCase getUserUseCase;
    private final UpdateUserUseCase updateUserUseCase;
    private final SearchUserUseCase searchUserUseCase;

    public UserController(
            LoginUserUseCase loginUserUseCase,
            CreateUserUseCase createUserUseCase,
            GetUserUseCase getUserUseCase,
            UpdateUserUseCase updateUserUseCase,
            SearchUserUseCase searchUserUseCase) {
        this.loginUserUseCase = loginUserUseCase;
        this.createUserUseCase = createUserUseCase;
        this.getUserUseCase = getUserUseCase;
        this.updateUserUseCase = updateUserUseCase;
        this.searchUserUseCase = searchUserUseCase;
    }

    @PostMapping("/login")
    public ResponseEntity<WebHttpResponse<UserPublicData>> loginUser(@Valid @RequestBody UserLoginData request)
            throws PasswordNotMatchException {
        UserAccountModel userAccountData = loginUserUseCase.execute(request);
        return new ResponseEntity<>(WebHttpResponse.ok(new UserPublicData(userAccountData)), HttpStatus.OK);
    }

    @PostMapping("/user")
    public ResponseEntity<WebHttpResponse<UserPublicData>> createUser(@Valid @RequestBody UserCreateData request) {
        UserAccountModel userAccountData = createUserUseCase.execute(request);
        return new ResponseEntity<>(WebHttpResponse.created(new UserPublicData(userAccountData)), HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<WebHttpResponse<List<UserPublicData>>> getAllUser() {
        List<UserAccountModel> userAccountDatas = searchUserUseCase.execute();
        return new ResponseEntity<>(
                WebHttpResponse.ok(userAccountDatas.stream().map(UserPublicData::new).toList()),
                HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<WebHttpResponse<UserPublicData>> getUserById(@PathVariable Long id) throws UserNotFoundException {
        UserAccountModel userAccountData = getUserUseCase.findById(id);
        return new ResponseEntity<>(WebHttpResponse.ok(new UserPublicData(userAccountData)), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<WebHttpResponse<UserPublicData>> updateUser(
            @PathVariable Long id,
            @Valid @RequestBody UserUpdateData request) {
        UserAccountModel userAccountData = updateUserUseCase.execute(id, request);
        return new ResponseEntity<>(WebHttpResponse.ok(new UserPublicData(userAccountData)), HttpStatus.OK);
    }

}
