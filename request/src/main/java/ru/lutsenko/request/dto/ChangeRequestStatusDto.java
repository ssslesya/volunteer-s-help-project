package ru.lutsenko.request.dto;

import lombok.Builder;
import lombok.Value;
import ru.lutsenko.request.entity.RequestStatus;

/**
 * DTO for {@link ru.lutsenko.request.entity.Request}
 */
@Value
@Builder
public class ChangeRequestStatusDto {
    Long id;
    Long needingId;
    Long volunteerId;
    String requestStatus;
}