package ru.lutsenko.request.service;

import ru.lutsenko.request.dto.ChangeRequestStatusDto;

public interface NotificationService {
    public void sendNotification(ChangeRequestStatusDto changeRequestStatusDto);
}
