package com.hendi.banktransfersystem.usecase.user.dto;

import java.math.BigDecimal;

public interface IUserUpdateData {

    String password();

    BigDecimal balance();

    Long roleId();

}
