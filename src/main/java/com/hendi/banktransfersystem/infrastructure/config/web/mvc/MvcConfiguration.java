package com.hendi.banktransfersystem.infrastructure.config.web.mvc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.hendi.banktransfersystem.entity.transaction.gateway.TransactionGateway;
import com.hendi.banktransfersystem.entity.user.gateway.UserGateway;
import com.hendi.banktransfersystem.entity.usertoken.gateway.UserTokenGateway;
import com.hendi.banktransfersystem.infrastructure.config.db.repository.TransactionRepository;
import com.hendi.banktransfersystem.infrastructure.config.db.repository.UserRepository;
import com.hendi.banktransfersystem.infrastructure.config.db.repository.UserTokenRepository;
import com.hendi.banktransfersystem.infrastructure.config.web.security.service.MyUserDetailService;
import com.hendi.banktransfersystem.infrastructure.config.web.security.util.JwtUtils;
import com.hendi.banktransfersystem.infrastructure.transaction.gateway.TransactionDatabaseGateway;
import com.hendi.banktransfersystem.infrastructure.user.gateway.UserDatabaseGateway;
import com.hendi.banktransfersystem.infrastructure.usertoken.gateway.UserTokenDatabaseGateway;
import com.hendi.banktransfersystem.usecase.transaction.GetTransactionUseCase;
import com.hendi.banktransfersystem.usecase.transaction.TransactionTransferUseCase;
import com.hendi.banktransfersystem.usecase.user.CreateUserUseCase;
import com.hendi.banktransfersystem.usecase.user.GetUserUseCase;
import com.hendi.banktransfersystem.usecase.user.LoginUserUseCase;
import com.hendi.banktransfersystem.usecase.user.SearchUserUseCase;
import com.hendi.banktransfersystem.usecase.user.UpdateUserUseCase;
import com.hendi.banktransfersystem.usecase.usertoken.CreateUserTokenUseCase;
import com.hendi.banktransfersystem.usecase.usertoken.DeleteUserTokenUseCase;
import com.hendi.banktransfersystem.usecase.usertoken.GetUserTokenUseCase;

@Configuration
public class MvcConfiguration {

    @Bean
    public CreateUserUseCase createUserUseCase(UserRepository userRepository) {
        UserGateway userGateway = new UserDatabaseGateway(userRepository);
        return new CreateUserUseCase(userGateway);
    }

    @Bean
    public GetUserUseCase getUserUseCase(UserRepository userRepository) {
        UserGateway userGateway = new UserDatabaseGateway(userRepository);
        return new GetUserUseCase(userGateway);
    }

    @Bean
    public LoginUserUseCase loginUserUseCase(
            GetUserUseCase getUserUseCase,
            CreateUserTokenUseCase createUserTokenUseCase,
            JwtUtils jwtUtils,
            MyUserDetailService myUserDetailService,
            PasswordEncoder passwordEncoder) {
        return new LoginUserUseCase(
                getUserUseCase,
                createUserTokenUseCase,
                jwtUtils,
                myUserDetailService,
                passwordEncoder);
    }

    @Bean
    public SearchUserUseCase searchUserUseCase(UserRepository userRepository) {
        UserGateway userGateway = new UserDatabaseGateway(userRepository);
        return new SearchUserUseCase(userGateway);
    }

    @Bean
    public UpdateUserUseCase updateUserUseCase(
            UserRepository userRepository,
            GetUserUseCase getUserUseCase,
            PasswordEncoder passwordEncoder) {
        UserGateway userGateway = new UserDatabaseGateway(userRepository);
        return new UpdateUserUseCase(userGateway, getUserUseCase, passwordEncoder);
    }

    @Bean
    public GetUserTokenUseCase getUserTokenUseCase(UserTokenRepository userTokenRepository) {
        UserTokenGateway userTokenGateway = new UserTokenDatabaseGateway(userTokenRepository);
        return new GetUserTokenUseCase(userTokenGateway);
    }

    @Bean
    public CreateUserTokenUseCase createUserTokenUseCase(UserTokenRepository userTokenRepository) {
        UserTokenGateway userTokenGateway = new UserTokenDatabaseGateway(userTokenRepository);
        return new CreateUserTokenUseCase(userTokenGateway);
    }

    @Bean
    public DeleteUserTokenUseCase deleteUserTokenUseCase(UserTokenRepository userTokenRepository) {
        UserTokenGateway userTokenGateway = new UserTokenDatabaseGateway(userTokenRepository);
        return new DeleteUserTokenUseCase(userTokenGateway);
    }

    @Bean
    public GetTransactionUseCase getTransactionUseCase(TransactionRepository transactionRepository) {
        TransactionGateway transactionGateway = new TransactionDatabaseGateway(transactionRepository);
        return new GetTransactionUseCase(transactionGateway);
    }

    @Bean
    public TransactionTransferUseCase transactionTransferUseCase(TransactionRepository transactionRepository,
            GetUserUseCase getUserUseCase) {
        TransactionGateway transactionGateway = new TransactionDatabaseGateway(transactionRepository);
        return new TransactionTransferUseCase(transactionGateway, getUserUseCase);
    }

}
