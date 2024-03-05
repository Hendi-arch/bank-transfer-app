package com.hendi.banktransfersystem.usecase.user;

import java.time.LocalDateTime;
import java.time.ZoneId;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.hendi.banktransfersystem.entity.user.exception.PasswordNotMatchException;
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
    private final PasswordEncoder passwordEncoder;

    public LoginUserUseCase(
            GetUserUseCase getUserUseCase,
            CreateUserTokenUseCase createUserTokenUseCase,
            JwtUtils jwtUtils,
            MyUserDetailService myUserDetailService,
            PasswordEncoder passwordEncoder) {
        this.getUserUseCase = getUserUseCase;
        this.createUserTokenUseCase = createUserTokenUseCase;
        this.jwtUtils = jwtUtils;
        this.myUserDetailService = myUserDetailService;
        this.passwordEncoder = passwordEncoder;
    }

    public UserAccountModel execute(IUserLoginData data) throws PasswordNotMatchException {
        String username = data.username();
        String password = data.password();

        UserAccountModel userAccountData = getUserUseCase.findByUsername(username);

        boolean isPasswordMatch = passwordEncoder.matches(password, userAccountData.getPassword());
        if (isPasswordMatch) {
            throw new PasswordNotMatchException();
        }

        UserDetails userDetails = myUserDetailService.loadUserByUsername(username);
        String jwtToken = jwtUtils.generateJwtToken(userDetails);
        LocalDateTime expiryDateTime = jwtUtils.getExpirationFromJwtToken(jwtToken).toInstant()
                .atZone(ZoneId.systemDefault()).toLocalDateTime();

        UserTokenCreateData userTokenData = new UserTokenCreateData(userAccountData, jwtToken, expiryDateTime);
        createUserTokenUseCase.execute(userTokenData);
        return userAccountData;
    }

}
