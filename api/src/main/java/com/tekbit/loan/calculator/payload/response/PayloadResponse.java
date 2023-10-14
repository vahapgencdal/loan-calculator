package com.tekbit.loan.calculator.payload.response;

import com.tekbit.loan.calculator.model.UserDateAudit;
import jakarta.persistence.MappedSuperclass;
import lombok.*;

import java.time.Instant;

@MappedSuperclass
@Data
public class PayloadResponse {

    private String createdBy;
    private String createdByTooltip;
    private String updatedBy;
    private String updatedByTooltip;
    private Instant createdAt;
    private Instant updatedAt;

    public PayloadResponse(String createdBy, String createdByTooltip, String updatedBy, String updatedByTooltip, Instant createdAt, Instant updatedAt) {
        this.createdBy = createdBy;
        this.createdByTooltip = createdByTooltip;
        this.updatedBy = updatedBy;
        this.updatedByTooltip = updatedByTooltip;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public PayloadResponse(Instant createdAt, Instant updatedAt) {
        this.createdBy = "VG";
        this.createdByTooltip = "Vahap Gencdal";
        this.updatedBy = "VG";
        this.updatedByTooltip = "Vahap Gencdal";
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
