package ru.lutsenko.notification.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import ru.lutsenko.notification.dto.ChangeRequestStatusDto;
import ru.lutsenko.notification.service.NotificationService;

@Component
@RequiredArgsConstructor
public class NotificationListener {
    private final NotificationService notificationService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @KafkaListener(topics = "request", groupId = "1")
    public void handle(String message) {

        try {
            ChangeRequestStatusDto changeRequestStatusDto = objectMapper.readValue(message, ChangeRequestStatusDto.class);

            notificationService.save(changeRequestStatusDto);
        } catch (Exception ex) {
            System.out.println("Exception while parsing");
        }
    }
}
