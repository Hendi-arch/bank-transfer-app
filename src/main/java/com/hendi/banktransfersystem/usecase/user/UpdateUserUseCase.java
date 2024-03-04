package com.hendi.banktransfersystem.usecase.user;

import java.math.BigDecimal;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.hendi.banktransfersystem.entity.user.gateway.UserGateway;
import com.hendi.banktransfersystem.entity.user.model.UserAccountModel;
import com.hendi.banktransfersystem.usecase.user.dto.IUserUpdateData;

public class UpdateUserUseCase {

    private final UserGateway userGateway;
    private final GetUserUseCase getUserUseCase;
    private final PasswordEncoder passwordEncoder;

    public UpdateUserUseCase(UserGateway userGateway, GetUserUseCase getUserUseCase, PasswordEncoder passwordEncoder) {
        this.userGateway = userGateway;
        this.getUserUseCase = getUserUseCase;
        this.passwordEncoder = passwordEncoder;
    }

    public UserAccountModel execute(Long id, IUserUpdateData data) {
        String username = data.username();
        String password = data.password();
        BigDecimal balance = data.balance();

        UserAccountModel userAccountData = getUserUseCase.findById(id);
        userAccountData.setUsername(username);
        userAccountData.setBalance(balance);

        String encodedPassword = passwordEncoder.encode(password);
        userAccountData.setPassword(encodedPassword);

        return userGateway.update(userAccountData);
    }

}
