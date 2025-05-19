package ru.lutsenko.request.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum RequestType {
    EASY_LIFE_HELP("Лёгкая бытовая помощь"),
    MASTERS_HELP("Помощь мастера"),
    PHYSICAL_HELP("Физическая помощь"),
    SOCIAL("Социальная поддержка"),
    TEAM_VOLUNTEER("Командная волонтёрская деятельность");

    private final String description;
}
