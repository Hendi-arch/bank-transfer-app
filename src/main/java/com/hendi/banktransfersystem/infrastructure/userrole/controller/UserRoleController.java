package com.hendi.banktransfersystem.infrastructure.userrole.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hendi.banktransfersystem.entity.userrole.exception.UserRoleNotFoundException;
import com.hendi.banktransfersystem.entity.userrole.model.UserRoleModel;
import com.hendi.banktransfersystem.infrastructure.config.web.response.WebHttpResponse;
import com.hendi.banktransfersystem.infrastructure.userrole.dto.UserRolePublicData;
import com.hendi.banktransfersystem.usecase.userrole.GetUserRoleUseCase;
import com.hendi.banktransfersystem.usecase.userrole.SearchUserRoleUseCase;

@RestController
@RequestMapping("/userroles")
public class UserRoleController {

    private final GetUserRoleUseCase getUserRoleUseCase;
    private final SearchUserRoleUseCase searchUserRoleUseCase;

    public UserRoleController(
            GetUserRoleUseCase getUserRoleUseCase,
            SearchUserRoleUseCase searchUserRoleUseCase) {
        this.getUserRoleUseCase = getUserRoleUseCase;
        this.searchUserRoleUseCase = searchUserRoleUseCase;
    }

    @GetMapping("/all")
    public ResponseEntity<WebHttpResponse<List<UserRolePublicData>>> getAllUserRole() {
        List<UserRoleModel> userRoleDatas = searchUserRoleUseCase.execute();
        return new ResponseEntity<>(
                WebHttpResponse.ok(userRoleDatas.stream().map(UserRolePublicData::new).toList()),
                HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<WebHttpResponse<UserRolePublicData>> getUserRoleById(@PathVariable Long id)
            throws UserRoleNotFoundException {
        UserRoleModel userRoleData = getUserRoleUseCase.execute(id);
        return new ResponseEntity<>(WebHttpResponse.ok(new UserRolePublicData(userRoleData)), HttpStatus.OK);
    }

}
