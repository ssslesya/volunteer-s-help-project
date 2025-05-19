package ru.lutsenko.request.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.lutsenko.request.dto.ChangeRequestStatusDto;
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

        ChangeRequestStatusDto changeRequestStatusDto = ChangeRequestStatusDto.builder()
                .id(request.getId())
                .requestStatus(request.getRequestStatus())
                .volunteerId(request.getVolunteerId())
                .needingId(request.getNeedingId())
                .build();

        notificationService.sendNotification(changeRequestStatusDto);

        return request;
    }

    public RequestDto setOrUpdateVolunteer(Long volunteerId, Long requestId) {
        RequestDto request = requestService.setOrUpdateVolunteer(volunteerId, requestId);

        ChangeRequestStatusDto changeRequestStatusDto = ChangeRequestStatusDto.builder()
                .id(request.getId())
                .requestStatus(request.getRequestStatus())
                .volunteerId(request.getVolunteerId())
                .needingId(request.getNeedingId())
                .build();

        notificationService.sendNotification(changeRequestStatusDto);

        return request;
    }

    public RequestDto updateRequest(UpdateRequestDto updateRequestDto) {
        RequestDto request = requestService.updateRequest(updateRequestDto);

        ChangeRequestStatusDto changeRequestStatusDto = ChangeRequestStatusDto.builder()
                .id(request.getId())
                .requestStatus(request.getRequestStatus())
                .volunteerId(request.getVolunteerId())
                .needingId(request.getNeedingId())
                .build();

        notificationService.sendNotification(changeRequestStatusDto);

        return request;
    }

    public RequestDto completeRequest(Long requestId) {
        RequestDto request = requestService.completeRequest(requestId);

        ChangeRequestStatusDto changeRequestStatusDto = ChangeRequestStatusDto.builder()
                .id(request.getId())
                .requestStatus(request.getRequestStatus())
                .volunteerId(request.getVolunteerId())
                .needingId(request.getNeedingId())
                .build();

        notificationService.sendNotification(changeRequestStatusDto);

        return request;
    }
}
