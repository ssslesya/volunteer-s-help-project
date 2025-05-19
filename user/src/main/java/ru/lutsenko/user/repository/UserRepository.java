package ru.lutsenko.user.repository;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.lutsenko.user.entity.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Override
    @EntityGraph(
            attributePaths = {"sentFeedbacks", "receivedFeedbacks"}
    )
    Optional<User> findById(@NotNull Long id);

    Optional<User> findByKeycloakId(UUID keycloakId);

    Page<User> findByIsApprovedAccountFalse(Pageable pageable);
}