package com.hendi.banktransfersystem.usecase.user;

import java.math.BigDecimal;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.hendi.banktransfersystem.entity.user.exception.UserNotFoundException;
import com.hendi.banktransfersystem.entity.user.gateway.UserGateway;
import com.hendi.banktransfersystem.entity.user.model.UserAccountModel;
import com.hendi.banktransfersystem.entity.userrole.exception.UserRoleNotFoundException;
import com.hendi.banktransfersystem.entity.userrole.gateway.UserRoleGateway;
import com.hendi.banktransfersystem.entity.userrole.model.UserRoleModel;
import com.hendi.banktransfersystem.usecase.user.dto.IUserUpdateData;

public class UpdateUserUseCase {

    private final UserGateway userGateway;
    private final UserRoleGateway userRoleGateway;
    private final PasswordEncoder passwordEncoder;

    public UpdateUserUseCase(
            UserGateway userGateway,
            UserRoleGateway userRoleGateway,
            PasswordEncoder passwordEncoder) {
        this.userGateway = userGateway;
        this.userRoleGateway = userRoleGateway;
        this.passwordEncoder = passwordEncoder;
    }

    public UserAccountModel execute(Long id, IUserUpdateData data)
            throws UserNotFoundException, UserRoleNotFoundException {
        String password = data.password();
        BigDecimal balance = data.balance();
        Long roleId = data.roleId();

        UserAccountModel userAccountData = userGateway.findById(id).orElseThrow(UserNotFoundException::new);
        userAccountData.setBalance(balance);

        String encodedPassword = passwordEncoder.encode(password);
        userAccountData.setPassword(encodedPassword);

        UserRoleModel userRole = userRoleGateway.findById(roleId).orElseThrow(UserRoleNotFoundException::new);
        userAccountData.setRole(userRole);

        return userGateway.update(userAccountData);
    }

}
