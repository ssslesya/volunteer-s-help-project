package ru.lutsenko.user.dto.user;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Value
@Builder
public class UserDto {
    Long id;
    String name;
    UUID keycloakId;
    String surname;
    String patronymic;
    String email;
    String passport;
    Boolean isVolunteer;
    LocalDate registrationDate;
    Boolean isApprovedAccount;
    List<String> documentIds;
    String avatarId;
}