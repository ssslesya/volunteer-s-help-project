package ru.lutsenko.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.lutsenko.user.dto.user.CreateUserDto;
import ru.lutsenko.user.dto.user.UserDto;
import ru.lutsenko.user.entity.User;
import ru.lutsenko.user.service.UserService;

import java.util.List;
import java.util.UUID;

@CrossOrigin("*")
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<UserDto> createUser(
            @RequestParam("keycloakId") String keycloakId,
            @RequestParam("name") String name,
            @RequestParam("surname") String surname,
            @RequestParam("isVolunteer") Boolean isVolunteer,
            @RequestPart("documents") List<MultipartFile> documents
    ) {
        CreateUserDto createUserDto = CreateUserDto.builder()
                .keycloakId(UUID.fromString(keycloakId))
                .name(name)
                .surname(surname)
                .isVolunteer(isVolunteer)
                .documents(documents)
                .build();

        UserDto userDto = userService.createUser(createUserDto);
        return ResponseEntity.ok(userDto);
    }

    @GetMapping("/exists/{id}")
    public ResponseEntity<Boolean> existsById(@PathVariable Long id) {
        boolean resultBoolean = userService.existsById(id);
        return ResponseEntity.ok(resultBoolean);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUserProfile(@PathVariable Long userId) {
        UserDto userDto = userService.getUserProfile(userId);
        return ResponseEntity.ok(userDto);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUserProfile(@PathVariable Long userId, @RequestBody UserDto updatedUser) {
        UserDto userDto = userService.updateUserProfile(userId, updatedUser);
        return ResponseEntity.ok(userDto);
    }

    @GetMapping("/keycloak/{keycloakId}")
    public ResponseEntity<UserDto> findByKeycloakId(@PathVariable String keycloakId) {
        UserDto user = userService.findByKeycloakId(UUID.fromString(keycloakId));
        return ResponseEntity.ok(user);
    }
}
