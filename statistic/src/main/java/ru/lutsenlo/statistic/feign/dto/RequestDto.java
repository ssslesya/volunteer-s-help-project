package ru.lutsenlo.statistic.feign.dto;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;


@Value
@Builder
public class RequestDto {
    Long id;
    String type;
    String description;
    Address address;
    LocalDateTime createdAt;
    LocalDateTime executionDateTime;
    LocalDateTime executedDateTime;
    Long needingId;
    Long volunteerId;
    String requestStatus;
}