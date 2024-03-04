package com.hendi.banktransfersystem.infrastructure.config.db.schema;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import com.hendi.banktransfersystem.entity.usertoken.model.UserTokenModel;

import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_tokens", uniqueConstraints = @UniqueConstraint(columnNames = { "token" }))
public class UserTokenSchema {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_user_id"))
    private UserSchema user;

    private String token;

    private LocalDateTime expiryDateTime;

    @CreatedBy
    private String createdBy;

    @LastModifiedBy
    private String updatedBy;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    public UserTokenSchema(UserTokenModel userTokenModel) {
        this.user = new UserSchema(userTokenModel.getUserAccount());
        this.token = userTokenModel.getToken();
        this.expiryDateTime = userTokenModel.getExpiryDateTime();
    }

    public UserTokenModel toUserTokenModel() {
        UserTokenModel userTokenModel = new UserTokenModel(
                this.user.toUserAccountModel(),
                this.token,
                this.expiryDateTime);
        userTokenModel.setId(this.id);
        userTokenModel.setCreatedAt(this.createdAt);
        userTokenModel.setUpdatedAt(this.updatedAt);
        userTokenModel.setCreatedBy(this.createdBy);
        userTokenModel.setUpdatedBy(this.updatedBy);
        return userTokenModel;
    }

}
