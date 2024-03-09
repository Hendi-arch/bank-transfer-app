package com.hendi.banktransfersystem.usecase.userrole.dto;

import java.time.LocalDateTime;

public interface IUserRolePublicData {

    Long id();

    String role();

    LocalDateTime createdAt();

    LocalDateTime updatedAt();

}
