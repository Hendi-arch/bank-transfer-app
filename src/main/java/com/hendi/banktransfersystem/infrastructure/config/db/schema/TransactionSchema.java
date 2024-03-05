package com.hendi.banktransfersystem.infrastructure.config.db.schema;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import com.hendi.banktransfersystem.entity.transaction.model.TransactionModel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "transactions")
public class TransactionSchema {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "sender_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_sender_id"))
    private UserSchema sender;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "receiver_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_receiver_id"))
    private UserSchema receiver;

    @NotNull
    @Column(precision = 15, scale = 2)
    private BigDecimal amount;

    @NotNull
    private LocalDateTime timestamp;

    @CreatedBy
    private String createdBy;

    @LastModifiedBy
    private String updatedBy;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
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