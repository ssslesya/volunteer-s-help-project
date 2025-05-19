package ru.lutsenko.request.dto;

import lombok.Value;
import ru.lutsenko.request.entity.RequestStatus;

/**
 * DTO for {@link ru.lutsenko.request.entity.Request}
 */
@Value
public class SmallRequestDto {
    Long id;
    Long needingId;
    Long volunteerId;
    String requestStatus;
}