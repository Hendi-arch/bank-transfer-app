package com.hendi.banktransfersystem.usecase.user;

import java.time.LocalDateTime;
import java.time.ZoneId;

import org.springframework.security.core.userdetails.UserDetails;

import com.hendi.banktransfersystem.entity.user.model.UserAccountModel;
import com.hendi.banktransfersystem.infrastructure.config.web.security.service.MyUserDetailService;
import com.hendi.banktransfersystem.infrastructure.config.web.security.util.JwtUtils;
import com.hendi.banktransfersystem.infrastructure.usertoken.dto.UserTokenCreateData;
import com.hendi.banktransfersystem.usecase.user.dto.IUserLoginData;
import com.hendi.banktransfersystem.usecase.usertoken.CreateUserTokenUseCase;

public class LoginUserUseCase {

    private final GetUserUseCase getUserUseCase;
    private final CreateUserTokenUseCase createUserTokenUseCase;
    private final JwtUtils jwtUtils;
    private final MyUserDetailService myUserDetailService;

    public LoginUserUseCase(
            GetUserUseCase getUserUseCase,
            CreateUserTokenUseCase createUserTokenUseCase,
            JwtUtils jwtUtils,
            MyUserDetailService myUserDetailService) {
        this.getUserUseCase = getUserUseCase;
        this.createUserTokenUseCase = createUserTokenUseCase;
        this.jwtUtils = jwtUtils;
        this.myUserDetailService = myUserDetailService;
    }

    public UserAccountModel execute(IUserLoginData data) {
        String username = data.username();

        UserDetails userDetails = myUserDetailService.loadUserByUsername(username);
        String jwtToken = jwtUtils.generateJwtToken(userDetails);
        LocalDateTime expiryDateTime = jwtUtils.getExpirationFromJwtToken(jwtToken).toInstant()
                .atZone(ZoneId.systemDefault()).toLocalDateTime();

        UserAccountModel userAccountData = getUserUseCase.findByUsername(username);

        UserTokenCreateData userTokenData = new UserTokenCreateData(userAccountData, jwtToken, expiryDateTime);
        createUserTokenUseCase.execute(userTokenData);
        return userAccountData;
    }

}
