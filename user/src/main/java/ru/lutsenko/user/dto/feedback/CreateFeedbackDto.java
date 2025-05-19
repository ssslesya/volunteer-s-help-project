package ru.lutsenko.user.dto.feedback;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CreateFeedbackDto {
    Long senderId;
    Long receiverId;
    Long requestId;
    String text;
    Short mark;
}