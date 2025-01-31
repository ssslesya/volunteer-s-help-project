package ru.lutsenko.request.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.lutsenko.request.dto.CreateRequestDto;
import ru.lutsenko.request.dto.RequestDto;
import ru.lutsenko.request.dto.UpdateRequestDto;

@Service
@RequiredArgsConstructor
public class RequestAndNotificationFacade {
    private final RequestService requestService;
    private final NotificationService notificationService;

    public RequestDto createRequest(CreateRequestDto createRequestDto) {
        RequestDto request = requestService.createRequest(createRequestDto);

        notificationService.sendNotification(createRequestDto.getNeedingId(),
                String.format("Создана заявка с id: %s", request.getId()));

        return request;
    }

    public RequestDto setOrUpdateVolunteer(Long volunteerId, Long requestId) {
        RequestDto request = requestService.setOrUpdateVolunteer(volunteerId, requestId);

        notificationService.sendNotification(volunteerId,
                String.format("Назначена заявка с id: %s", requestId));
        notificationService.sendNotification(request.getNeedingId(),
                String.format("Назначен волнтер с id: %s", volunteerId));

        return request;
    }

    public RequestDto updateRequest(UpdateRequestDto updateRequestDto) {
        RequestDto request = requestService.updateRequest(updateRequestDto);

        notificationService.sendNotification(request.getNeedingId(),
                String.format("Обновлена заявка с id: %s", request.getId()));

        return request;
    }

    public RequestDto completeRequest(Long requestId) {
        RequestDto request = requestService.completeRequest(requestId);

        notificationService.sendNotification(request.getNeedingId(),
                String.format("Завершена заявка с id: %s", request.getId()));

        return request;
    }
}
