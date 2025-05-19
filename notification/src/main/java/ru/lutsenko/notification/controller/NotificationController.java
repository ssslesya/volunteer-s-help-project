package ru.lutsenko.notification.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.lutsenko.notification.entity.Notification;
import ru.lutsenko.notification.service.NotificationService;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/notification")
@RequiredArgsConstructor
public class NotificationController {
    private final NotificationService notificationService;

    @GetMapping("/{id}")
    public List<Notification> findById(@PathVariable Long id) {
        return notificationService.findByUserIdOrderByCreatedAtAsc(id);
    }

    @DeleteMapping("/{notificationId}")
    public void deleteByNotificationId(@PathVariable Long notificationId) {
        notificationService.deleteById(notificationId);
    }
}
