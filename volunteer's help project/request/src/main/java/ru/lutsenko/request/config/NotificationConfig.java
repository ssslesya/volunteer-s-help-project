package ru.lutsenko.request.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.lutsenko.request.service.NotificationService;

@Slf4j
@Configuration
public class NotificationConfig {
    @Bean
    public NotificationService notificationService() {
        return (userId, message) -> log.info(message);
    }
}
