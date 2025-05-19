package ru.lutsenko.user.dto.user;

import lombok.Builder;
import lombok.Value;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;


@Value
@Builder
public class CreateUserDto {
    UUID keycloakId;
    String name;
    String surname;
    String patronymic;
    Boolean isVolunteer;
    String email;
    String passport;
    List<MultipartFile> documents;
}