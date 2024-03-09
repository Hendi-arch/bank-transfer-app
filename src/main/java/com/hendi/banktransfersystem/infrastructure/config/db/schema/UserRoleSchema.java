package com.hendi.banktransfersystem.infrastructure.config.db.schema;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.hendi.banktransfersystem.entity.userrole.model.UserRoleModel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
@Table(name = "user_roles")
public class UserRoleSchema {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    @Enumerated(EnumType.STRING)
    private RoleEnum role;

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

    public UserRoleSchema(UserRoleModel userRoleModel) {
        this.id = userRoleModel.getId();
        this.role = userRoleModel.getRole();
        this.createdBy = userRoleModel.getCreatedBy();
        this.updatedBy = userRoleModel.getUpdatedBy();
        this.createdAt = userRoleModel.getCreatedAt();
        this.updatedAt = userRoleModel.getUpdatedAt();
    }

    public static List<UserRoleSchema> toUserRoleSchemaList(List<UserRoleModel> userRoleModels) {
        return userRoleModels.stream().map(UserRoleSchema::new).toList();
    }

    public UserRoleModel toUserRoleModel() {
        UserRoleModel userRoleModel = new UserRoleModel(this.role);
        userRoleModel.setId(this.id);
        userRoleModel.setCreatedAt(this.createdAt);
        userRoleModel.setUpdatedAt(this.updatedAt);
        userRoleModel.setCreatedBy(this.createdBy);
        userRoleModel.setUpdatedBy(this.updatedBy);
        return userRoleModel;
    }

    public enum RoleEnum {
        USER,
        ADMIN,
        SUPER_ADMIN
    }

}
