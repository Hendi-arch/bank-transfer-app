package com.hendi.banktransfersystem.usecase.user;

import java.time.LocalDateTime;
import java.time.ZoneId;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.hendi.banktransfersystem.entity.user.exception.PasswordNotMatchException;
import com.hendi.banktransfersystem.entity.user.exception.UserNotFoundException;
import com.hendi.banktransfersystem.entity.user.gateway.UserGateway;
import com.hendi.banktransfersystem.entity.user.model.UserAccountModel;
import com.hendi.banktransfersystem.entity.usertoken.gateway.UserTokenGateway;
import com.hendi.banktransfersystem.entity.usertoken.model.UserTokenModel;
import com.hendi.banktransfersystem.infrastructure.config.web.security.service.MyUserDetailService;
import com.hendi.banktransfersystem.infrastructure.config.web.security.util.JwtUtils;
import com.hendi.banktransfersystem.usecase.user.dto.IUserLoginData;

public class LoginUserUseCase {

    private final UserGateway userGateway;
    private final UserTokenGateway userTokenGateway;
    private final JwtUtils jwtUtils;
    private final MyUserDetailService myUserDetailService;
    private final PasswordEncoder passwordEncoder;

    public LoginUserUseCase(
            UserGateway userGateway,
            UserTokenGateway userTokenGateway,
            JwtUtils jwtUtils,
            MyUserDetailService myUserDetailService,
            PasswordEncoder passwordEncoder) {
        this.userGateway = userGateway;
        this.userTokenGateway = userTokenGateway;
        this.jwtUtils = jwtUtils;
        this.myUserDetailService = myUserDetailService;
        this.passwordEncoder = passwordEncoder;
    }

    public UserAccountModel execute(IUserLoginData data) throws UserNotFoundException, PasswordNotMatchException {
        String username = data.username();
        String password = data.password();

        UserAccountModel userAccountData = userGateway.findByUsername(username).orElseThrow(UserNotFoundException::new);

        boolean isPasswordMatch = passwordEncoder.matches(password, userAccountData.getPassword());
        if (isPasswordMatch) {
            throw new PasswordNotMatchException();
        }

        UserDetails userDetails = myUserDetailService.loadUserByUsername(username);
        String jwtToken = jwtUtils.generateJwtToken(userDetails);
        LocalDateTime jwtExpiryDateTime = jwtUtils.getExpirationFromJwtToken(jwtToken).toInstant()
                .atZone(ZoneId.systemDefault()).toLocalDateTime();

        userAccountData.setJwtToken(jwtToken);
        userAccountData.setJwtExpiryDateTime(jwtExpiryDateTime);

        UserTokenModel userTokenData = new UserTokenModel(userAccountData, jwtToken, jwtExpiryDateTime);
        userTokenGateway.create(userTokenData);
        return userAccountData;
    }

}
