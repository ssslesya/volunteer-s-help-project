package ru.lutsenlo.statistic.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.lutsenlo.statistic.feign.FeedbackFeign;
import ru.lutsenlo.statistic.feign.RequestFeign;
import ru.lutsenlo.statistic.feign.dto.RequestDto;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StatisticsService {

    private final String CLOSED = "Завершено";

    private final RequestFeign requestFeign;
    private final FeedbackFeign feedbackFeign;

    public Double avgMarkByUserId(Long userId) {
        return feedbackFeign.avgMarkById(userId);
    }

    public Map<LocalDate, Long> requestsByUserId(Long userId) {
        List<RequestDto> requests = requestFeign.findAllByVolunteerId(userId);

        return requests.stream()
                .filter(request -> request.getExecutedDateTime() != null && CLOSED.equals(request.getRequestStatus()))
                .collect(Collectors.groupingBy(request -> request.getExecutedDateTime().toLocalDate(), Collectors.counting()));
    }

    public Map<String, Long> chartByUserId(Long userId) {
        List<RequestDto> requests = requestFeign.findAllByVolunteerId(userId);

        return requests.stream()
                .filter(request -> request.getExecutedDateTime() != null && CLOSED.equals(request.getRequestStatus()))
                .collect(Collectors.groupingBy(RequestDto::getType, Collectors.counting()));
    }
}
