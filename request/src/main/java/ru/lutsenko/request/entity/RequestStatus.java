package ru.lutsenko.request.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum RequestStatus {
    PENDING("Поиск волонтёра"),
    IN_PROGRESS("Волонтёр найден"),
    CLOSED("Завершено");

    private final String description;
}
