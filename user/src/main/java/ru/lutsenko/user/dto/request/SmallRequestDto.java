package ru.lutsenko.user.dto.request;

import lombok.Value;


@Value
public class SmallRequestDto {
    Long id;
    Long needingId;
    Long volunteerId;
    String requestStatus;
}