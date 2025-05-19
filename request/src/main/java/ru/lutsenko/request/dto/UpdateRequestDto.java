package ru.lutsenko.request.dto;

import lombok.Builder;
import lombok.Value;
import ru.lutsenko.request.entity.Address;

import java.time.LocalDateTime;

/**
 * DTO for {@link ru.lutsenko.request.entity.Request}
 */
@Value
@Builder
public class UpdateRequestDto {
    Long id;
    String description;
    Address address;
    LocalDateTime executionDateTime;
}