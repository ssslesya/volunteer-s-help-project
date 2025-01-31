package ru.lutsenko.request.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private RequestType type;

    @Column(name = "description", columnDefinition = "text")
    private String description;

    @Column(name = "address")
    @JdbcTypeCode(SqlTypes.JSON)
    private Address address;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "execution_date_time")
    private LocalDateTime executionDateTime;

    @Column(name = "executed_date_time")
    private LocalDateTime executedDateTime;

    @Column(name = "needing_id")
    private Long needingId;

    @Column(name = "volunteer_id")
    private Long volunteerId;

    @Enumerated(EnumType.STRING)
    @Column(name = "request_status")
    private RequestStatus requestStatus;
}
