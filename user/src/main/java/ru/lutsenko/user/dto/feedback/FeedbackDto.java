package ru.lutsenko.user.dto.feedback;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;


@Value
@Builder
public class FeedbackDto {
    Long id;
    Long senderId;
    Long receiverId;
    Long requestId;
    LocalDateTime dateTime;
    String text;
    Short mark;
}