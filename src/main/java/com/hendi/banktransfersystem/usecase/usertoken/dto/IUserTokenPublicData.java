package com.hendi.banktransfersystem.usecase.usertoken.dto;

import java.time.LocalDateTime;

public interface IUserTokenPublicData {
    
    String token();

    LocalDateTime expiryDateTime();

}
