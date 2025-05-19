package ru.lutsenko.notification.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.lutsenko.notification.dto.ChangeRequestStatusDto;
import ru.lutsenko.notification.entity.Notification;
import ru.lutsenko.notification.repository.NotificationRepository;

import java.time.LocalDateTime;
import java.util.List;

import static ru.lutsenko.notification.dto.RequestStatus.CLOSED;
import static ru.lutsenko.notification.dto.RequestStatus.IN_PROGRESS;
import static ru.lutsenko.notification.dto.RequestStatus.PENDING;

@Service
@RequiredArgsConstructor
public class NotificationService {
    private final NotificationRepository notificationRepository;

    public void save(ChangeRequestStatusDto changeRequestStatusDto) {

        if (PENDING.getDescription().equals(changeRequestStatusDto.getRequestStatus())) {

            Notification notification = Notification.builder()
                    .userId(changeRequestStatusDto.getNeedingId())
                    .message("Создана заявка")
                    .createdAt(LocalDateTime.now())
                    .build();

            notificationRepository.save(notification);
        }

        if (IN_PROGRESS.getDescription().equals(changeRequestStatusDto.getRequestStatus())) {
            Notification notification = Notification.builder()
                    .userId(changeRequestStatusDto.getNeedingId())
                    .createdAt(LocalDateTime.now())
                    .message("На заявку откликнулся волонтёр. Можете посмотреть его профиль через страницу заявки.")
                    .build();

            notificationRepository.save(notification);
        }

        if (CLOSED.getDescription().equals(changeRequestStatusDto.getRequestStatus())) {
            Notification notification = Notification.builder()
                    .userId(changeRequestStatusDto.getVolunteerId())
                    .createdAt(LocalDateTime.now())
                    .message("Нуждаюшийся завершил заявку. Посмотрите отзыв у себя в профиле.")
                    .build();

            notificationRepository.save(notification);
        }

    }

    public List<Notification> findByUserIdOrderByCreatedAtAsc(Long userId) {
        return notificationRepository.findByUserIdOrderByCreatedAtAsc(userId, PageRequest.of(0, 10));
    }

    public void deleteById(Long id) {
        notificationRepository.deleteById(id);
    }
}
