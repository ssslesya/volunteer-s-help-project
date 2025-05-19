package ru.lutsenko.request.dto;

import lombok.Builder;
import ru.lutsenko.request.entity.Address;
import ru.lutsenko.request.entity.RequestStatus;
import ru.lutsenko.request.entity.RequestType;
import lombok.Value;
import ru.lutsenko.request.entity.Request;

import java.time.LocalDateTime;

/**
 * DTO for {@link Request}
 */
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