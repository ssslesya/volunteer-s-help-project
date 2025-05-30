package ru.lutsenko.request.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.lutsenko.request.entity.Request;
import ru.lutsenko.request.entity.RequestStatus;
import ru.lutsenko.request.entity.RequestType;

import java.util.Collection;
import java.util.List;

public interface RequestRepository extends JpaRepository<Request, Long> {
    List<Request> findByNeedingId(Long needingId);

    List<Request> findByVolunteerId(Long volunteerId);

    List<Request> findByRequestStatus(RequestStatus requestStatus);

    List<Request> findByType(RequestType type);

    List<Request> findByRequestStatusIn(Collection<RequestStatus> requestStatuses, Pageable pageable);
}