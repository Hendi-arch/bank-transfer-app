package com.hendi.banktransfersystem.usecase.user;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.hendi.banktransfersystem.entity.user.gateway.UserGateway;
import com.hendi.banktransfersystem.entity.user.model.UserAccountModel;
import com.hendi.banktransfersystem.usecase.user.dto.IUserCreateData;
import org.springframework.security.crypto.password.PasswordEncoder;

public class CreateUserUseCase {

    private final UserGateway userGateway;
    private final PasswordEncoder passwordEncoder;

    public CreateUserUseCase(UserGateway userGateway, PasswordEncoder passwordEncoder) {
        this.userGateway = userGateway;
        this.passwordEncoder = passwordEncoder;
    }

    public UserAccountModel execute(IUserCreateData data) {
        String username = data.username();
        String password = passwordEncoder.encode(data.password());
        BigDecimal balance = data.balance();

        UserAccountModel userAccount = new UserAccountModel(username, password, balance);
        return userGateway.create(userAccount);
    }

}
