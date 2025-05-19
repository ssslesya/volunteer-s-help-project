package ru.lutsenko.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.lutsenko.user.dto.user.UserDto;
import ru.lutsenko.user.service.AdminService;

@CrossOrigin("*")
@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @GetMapping("/users")
    public Page<UserDto> findAllNotApproved(Pageable pageable) {
        return adminService.findAllNotApproved(pageable);
    }

    @PatchMapping("/users/{id}")
    public ResponseEntity<Void> approveUserById(@PathVariable Long id) {
        adminService.approveUserById(id);
        return ResponseEntity.noContent().build();
    }
}
