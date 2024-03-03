package com.hendi.banktransfersystem.entity.user.model;

import com.hendi.banktransfersystem.entity.AbstractEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class UserModel extends AbstractEntity<Long> {

    private String username;

    private String password;

}
