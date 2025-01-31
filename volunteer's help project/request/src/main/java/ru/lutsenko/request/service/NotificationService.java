package ru.lutsenko.request.service;

public interface NotificationService {
    public void sendNotification(Long userId, String message);
}
