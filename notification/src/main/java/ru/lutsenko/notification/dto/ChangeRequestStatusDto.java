package ru.lutsenko.notification.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChangeRequestStatusDto {
    Long id;
    Long needingId;
    Long volunteerId;
    String requestStatus;
}