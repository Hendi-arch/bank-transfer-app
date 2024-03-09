package com.hendi.banktransfersystem.infrastructure.config.db.schema;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.hendi.banktransfersystem.entity.transaction.model.TransactionModel;

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
@Table(name = "transactions")
public class TransactionSchema {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "sender_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_sender_id"), nullable = false)
    private UserSchema sender;

    @ManyToOne
    @JoinColumn(name = "receiver_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_receiver_id"), nullable = false)
    private UserSchema receiver;

    @Column(precision = 15, scale = 2, nullable = false)
    private BigDecimal amount;

    @Column(nullable = false)
    private LocalDateTime timestamp;

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

    public TransactionSchema(TransactionModel transactionModel) {
        this.sender = new UserSchema(transactionModel.getSender());
        this.receiver = new UserSchema(transactionModel.getReceiver());
        this.amount = transactionModel.getAmount();
        this.timestamp = transactionModel.getTimestamp();
    }

    public TransactionModel toTransactionModel() {
        TransactionModel transactionModel = new TransactionModel(
                this.sender.toUserAccountModel(),
                this.receiver.toUserAccountModel(),
                this.amount,
                this.timestamp);
        transactionModel.setId(this.id);
        transactionModel.setCreatedAt(this.createdAt);
        transactionModel.setUpdatedAt(this.updatedAt);
        transactionModel.setCreatedBy(this.createdBy);
        transactionModel.setUpdatedBy(this.updatedBy);
        return transactionModel;
    }

}