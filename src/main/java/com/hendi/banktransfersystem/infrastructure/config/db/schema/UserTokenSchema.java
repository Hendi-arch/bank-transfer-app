package com.hendi.banktransfersystem.infrastructure.config.db.schema;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.hendi.banktransfersystem.entity.usertoken.model.UserTokenModel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@EntityListeners(AuditingEntityListener.class)
@Table(name = "user_tokens", uniqueConstraints = @UniqueConstraint(columnNames = { "token" }))
public class UserTokenSchema {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_user_id"), nullable = false)
    private UserSchema user;

    @Column(length = 1000, nullable = false)
    private String token;

    private LocalDateTime expiryDateTime;

    @CreatedBy
    @Column(nullable = false)
    private String createdBy;

    @LastModifiedBy
    @Column(nullable = false)
    private String updatedBy;

    @CreatedDate
    @Column(nullable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime updatedAt;

    public UserTokenSchema(UserTokenModel userTokenModel) {
        this.user = new UserSchema(userTokenModel.getUserAccount());
        this.token = userTokenModel.getToken();
        this.expiryDateTime = userTokenModel.getExpiryDateTime();
        this.createdBy = userTokenModel.getCreatedBy();
        this.updatedBy = userTokenModel.getUpdatedBy();
        this.createdAt = userTokenModel.getCreatedAt();
        this.updatedAt = userTokenModel.getUpdatedAt();
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
