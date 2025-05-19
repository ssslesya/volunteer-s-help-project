package ru.lutsenko.user.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import ru.lutsenko.user.dto.feedback.CreateFeedbackDto;
import ru.lutsenko.user.dto.feedback.FeedbackDto;
import ru.lutsenko.user.entity.Feedback;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface FeedbackMapper {
    Feedback toFeedback(CreateFeedbackDto createFeedbackDto);

    @Mapping(source = "receiver.id" , target = "receiverId")
    @Mapping(source = "sender.id" , target = "senderId")
    FeedbackDto toDto(Feedback feedback);
}
