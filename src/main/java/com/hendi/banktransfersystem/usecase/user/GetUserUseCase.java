package com.hendi.banktransfersystem.usecase.user;

import java.math.BigDecimal;

import com.hendi.banktransfersystem.entity.transaction.exception.InsufficientBalanceException;
import com.hendi.banktransfersystem.entity.user.exception.UserNotFoundException;
import com.hendi.banktransfersystem.entity.user.gateway.UserGateway;
import com.hendi.banktransfersystem.entity.user.model.UserAccountModel;

public class GetUserUseCase {

    private final UserGateway userGateway;

    public GetUserUseCase(UserGateway userGateway) {
        this.userGateway = userGateway;
    }

    public UserAccountModel findById(Long id) throws UserNotFoundException {
        return userGateway
                .findById(id)
                .orElseThrow(UserNotFoundException::new);
    }

    public UserAccountModel findByUsername(String username) throws UserNotFoundException {
        return userGateway
                .findByUsername(username)
                .orElseThrow(UserNotFoundException::new);
    }

    public void verifySufficientBalanceForTransaction(Long senderId, BigDecimal transactionAmount)
            throws InsufficientBalanceException {
        if (!userGateway.hasSufficientBalanceForTransaction(senderId, transactionAmount)) {
            throw new InsufficientBalanceException();
        }
    }

}
