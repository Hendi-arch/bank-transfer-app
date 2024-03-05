package com.hendi.banktransfersystem.usecase.user.dto;

import java.math.BigDecimal;

public interface IUserCreateData {

    String username();

    String password();

    BigDecimal balance();

}
