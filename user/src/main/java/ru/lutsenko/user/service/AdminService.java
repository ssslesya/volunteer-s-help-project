package ru.lutsenko.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.lutsenko.user.dto.user.UserDto;
import ru.lutsenko.user.entity.User;
import ru.lutsenko.user.mapper.UserMapper;
import ru.lutsenko.user.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;

    public Page<UserDto> findAllNotApproved(Pageable pageable) {
        return userRepository.findByIsApprovedAccountFalse(pageable)
                .map(userMapper::toDto);
    }

    @Transactional
    public void approveUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(RuntimeException::new);

        user.setIsApprovedAccount(true);

        userRepository.save(user);
    }

}
