package ru.lutsenko.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.lutsenko.user.dto.user.CreateUserDto;
import ru.lutsenko.user.dto.user.UserDto;
import ru.lutsenko.user.entity.User;
import ru.lutsenko.user.mapper.UserMapper;
import ru.lutsenko.user.repository.UserRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class UserService {
    public final UserMapper userMapper;
    public final UserRepository userRepository;

    private final RestService restService;

    public boolean existsById(Long id) {
        return userRepository.existsById(id);
    }

    public User findById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Пользователь с ID " + userId + " не найден"));
    }

    public UserDto getUserProfile(Long userId) {
        return userRepository.findById(userId)
                .map(userMapper::toDto)
                .orElseThrow(() -> new IllegalArgumentException("Пользователь с ID " + userId + " не найден"));
    }

    public UserDto createUser(CreateUserDto createUserDto) {
        User user = userMapper.toUser(createUserDto);

        user.setIsApprovedAccount(false);
        user.setRegistrationDate(LocalDate.now());
        List<String> ids = restService.saveFiles(createUserDto.getDocuments());
        user.setDocumentIds(ids);

        return userMapper.toDto(userRepository.save(user));
    }

    @Transactional
    public UserDto updateUserProfile(Long userId, UserDto updatedUser) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Пользователь с ID " + userId + " не найден"));

        if (updatedUser.getName() != null) {
            user.setName(updatedUser.getName());
        }
        if (updatedUser.getSurname() != null) {
            user.setSurname(updatedUser.getSurname());
        }
        if (updatedUser.getPatronymic() != null) {
            user.setPatronymic(updatedUser.getPatronymic());
        }
        if (updatedUser.getEmail() != null) {
            user.setEmail(updatedUser.getEmail());
        }
        if (updatedUser.getPassport() != null) {
            user.setPassport(updatedUser.getPassport());
        }
        if (updatedUser.getIsVolunteer() != null) {
            user.setIsVolunteer(updatedUser.getIsVolunteer());
        }
        if (updatedUser.getRegistrationDate() != null) {
            user.setRegistrationDate(updatedUser.getRegistrationDate());
        }
        if (updatedUser.getIsApprovedAccount() != null) {
            user.setIsApprovedAccount(updatedUser.getIsApprovedAccount());
        }
        if (updatedUser.getAvatarId() != null) {
            user.setAvatarId(updatedUser.getAvatarId());
        }

        return userMapper.toDto(userRepository.save(user));
    }

    public UserDto findByKeycloakId(UUID keycloakId) {
        return userRepository.findByKeycloakId(keycloakId)
                .map(userMapper::toDto)
                .orElseThrow(() -> new IllegalArgumentException("Пользователь с ID " + keycloakId + " не найден"));
    }
}
