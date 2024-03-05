package com.hendi.banktransfersystem.infrastructure.config.db.schema;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import com.hendi.banktransfersystem.entity.user.model.UserAccountModel;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users", uniqueConstraints = @UniqueConstraint(name = "users", columnNames = { "username" }))
public class UserSchema {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 50)
    private String username;

    @NotBlank
    @Size(max = 100)
    private String password;

    @NotNull
    private BigDecimal balance;

    @CreatedBy
    private String createdBy;

    @LastModifiedBy
    private String updatedBy;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    public UserSchema(UserAccountModel userAccountModel) {
        this.username = userAccountModel.getUsername();
        this.password = userAccountModel.getPassword();
        this.balance = userAccountModel.getBalance();
    }

    public UserAccountModel toUserAccountModel() {
        UserAccountModel userAccountModel = new UserAccountModel(this.username, this.password, this.balance);
        userAccountModel.setId(this.id);
        userAccountModel.setCreatedAt(this.createdAt);
        userAccountModel.setUpdatedAt(this.updatedAt);
        userAccountModel.setCreatedBy(this.createdBy);
        userAccountModel.setUpdatedBy(this.updatedBy);
        return userAccountModel;
    }

}
