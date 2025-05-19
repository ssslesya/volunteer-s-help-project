package ru.lutsenko.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import ru.lutsenko.user.dto.feedback.CreateFeedbackDto;
import ru.lutsenko.user.dto.feedback.FeedbackDto;
import ru.lutsenko.user.dto.request.RequestStatus;
import ru.lutsenko.user.dto.request.SmallRequestDto;
import ru.lutsenko.user.entity.Feedback;
import ru.lutsenko.user.mapper.FeedbackMapper;
import ru.lutsenko.user.repository.FeedbackRepository;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@RequiredArgsConstructor
@Service
public class FeedbackService {
    private final RestService restService;
    private final UserService userService;

    private final FeedbackMapper feedbackMapper;
    private final FeedbackRepository feedbackRepository;


    public FeedbackDto sendFeedback(CreateFeedbackDto createFeedbackDto) {
        feedbackRepository.findByRequestId(createFeedbackDto.getRequestId())
                .ifPresent(feedback -> {
                    throw new IllegalArgumentException("По заявке с id: " + createFeedbackDto.getRequestId() + " уже оставлен отзыв с оценкой " + feedback.getMark());
                });


        if (!userService.existsById(createFeedbackDto.getReceiverId())) {
            throw new IllegalArgumentException("Пользователь с ID " + createFeedbackDto.getReceiverId() + " не найден");
        }
        if (!userService.existsById(createFeedbackDto.getSenderId())) {
            throw new IllegalArgumentException("Пользователь с ID " + createFeedbackDto.getSenderId() + " не найден");
        }

        SmallRequestDto request = restService.getRequestById(createFeedbackDto.getRequestId());

        if (!request.getRequestStatus().equals(RequestStatus.CLOSED.getDecription())) {
            throw new IllegalArgumentException("Невозможно оставить отхыв на незавршенныую заявку");
        }

        if (!createFeedbackDto.getSenderId().equals(request.getNeedingId())) {
            throw new IllegalArgumentException("Id нуждающегося и отпраитля отзыва не совпадает");
        }

        if (!createFeedbackDto.getReceiverId().equals(request.getVolunteerId())) {
            throw new IllegalArgumentException("Id волонтера в заявке и отзыве не совпадает");
        }

        Feedback feedback = Feedback.builder()
                .mark(createFeedbackDto.getMark())
                .text(createFeedbackDto.getText())
                .dateTime(LocalDateTime.now())
                .requestId(createFeedbackDto.getRequestId())
                .sender(userService.findById(createFeedbackDto.getSenderId()))
                .receiver(userService.findById(createFeedbackDto.getReceiverId()))
                .build();

        return feedbackMapper.toDto(feedbackRepository.save(feedback));
    }

    public List<FeedbackDto> findAllByReceiverId(Long id) {
        if (!userService.existsById(id)) {
            throw new IllegalArgumentException("Пользователь с ID " + id + " не найден");
        }

        return feedbackRepository.findByReceiver_Id(id).stream()
                .sorted(Comparator.comparing(Feedback::getDateTime))
                .map(feedbackMapper::toDto)
                .toList();
    }

    public Double avgMarkById(Long id) {
        if (!userService.existsById(id)) {
            throw new IllegalArgumentException("Пользователь с ID " + id + " не найден");
        }

        return feedbackRepository.findByReceiver_Id(id).stream()
                .mapToDouble(Feedback::getMark)
                .average()
                .orElse(0);
    }

    public Boolean existsByRequestId(Long requestId) {
        return feedbackRepository.existsByRequestId(requestId);
    }
}
