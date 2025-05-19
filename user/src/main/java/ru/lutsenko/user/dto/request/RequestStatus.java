package ru.lutsenko.user.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RequestStatus {
    PENDING("Поиск волонтёра"),
    IN_PROGRESS("Волонтёр найден"),
    CLOSED("Завершено");

    private final String decription;
}
