package com.example.tutorials.util.entity;

import com.example.tutorials.authentification.entity.AppUser;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import lombok.extern.log4j.Log4j2;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.time.Instant;

@NoArgsConstructor
@SuperBuilder
@Log4j2
@Data
public class AbstractEntity implements Serializable {

    private static final long serialVersionUID = 8756783731814650395L;

    @CreationTimestamp
    @Column(name = "created_at")
    protected Instant createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    protected Instant updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "auth_app_user_created_by_id")
    protected AppUser createdBy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "auth_app_user_updated_by_id")
    protected AppUser updatedBy;

    public String getLogDetails() {
        return getClass().getSimpleName();
    }
}
