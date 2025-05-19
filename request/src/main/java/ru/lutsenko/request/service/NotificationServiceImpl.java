package ru.lutsenko.request.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.lutsenko.request.dto.ChangeRequestStatusDto;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {
    @Value("${spring.kafka.requestTopic}")
    private String topic;
    private final KafkaTemplate<String, ChangeRequestStatusDto> kafkaTemplate;

    @Override
    public void sendNotification(ChangeRequestStatusDto changeRequestStatusDto) {
        kafkaTemplate.send("request", changeRequestStatusDto);

    }
}
