package com.tekbit.loan.calculator.model;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serial;

@EqualsAndHashCode(callSuper = true)
@MappedSuperclass
@Data
@EntityListeners(AuditingEntityListener.class)
public abstract class UserDateAudit extends DateAudit {
    @Serial
    private static final long serialVersionUID = 1L;

    @CreatedBy
    @Column(updatable = false)
    private Long createdBy;

    @LastModifiedBy
    private Long updatedBy;
}
