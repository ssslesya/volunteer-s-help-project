package ru.lutsenko.request.dto;

import ru.lutsenko.request.entity.Address;
import ru.lutsenko.request.entity.RequestType;
import lombok.Value;
import ru.lutsenko.request.entity.Request;

import java.time.LocalDateTime;

/**
 * DTO for {@link Request}
 */
@Value
public class  CreateRequestDto {
    RequestType type;
    String description;
    Address address;
    LocalDateTime executionDateTime;
    Long needingId;
}