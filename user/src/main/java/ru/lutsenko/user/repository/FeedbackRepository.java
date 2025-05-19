package ru.lutsenko.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.lutsenko.user.entity.Feedback;

import java.util.List;
import java.util.Optional;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Long> {


    List<Feedback> findByReceiver_Id(Long id);

    Optional<Feedback> findByRequestId(Long requestId);

    boolean existsByRequestId(Long requestId);
}