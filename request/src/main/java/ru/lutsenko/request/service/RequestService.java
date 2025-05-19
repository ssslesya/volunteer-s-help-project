package ru.lutsenko.request.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.lutsenko.request.dto.CreateRequestDto;
import ru.lutsenko.request.dto.RequestDto;
import ru.lutsenko.request.dto.UpdateRequestDto;
import ru.lutsenko.request.entity.Request;
import ru.lutsenko.request.entity.RequestStatus;
import ru.lutsenko.request.entity.RequestType;
import ru.lutsenko.request.mapper.RequestMapper;
import ru.lutsenko.request.repository.RequestRepository;

import java.time.LocalDateTime;
import java.util.List;


@Service
@RequiredArgsConstructor
public class RequestService {
    private final RequestMapper requestMapper;
    private final RequestRepository requestRepository;

    public RequestDto findById(Long id) {
        return requestRepository.findById(id)
                .map(requestMapper::toRequestDto)
                .orElseThrow(() -> new RuntimeException(String.format("Не найдена заявка с id: %s", id)));
    }

    public RequestDto createRequest(CreateRequestDto createRequestDto) {
        //TODO создать метод проверяющий существует ли пользователь
        if (false) {
            throw new RuntimeException(String.format("No such user with id: %s", createRequestDto.getNeedingId()));
        }

        Request request = requestMapper.toEntity(createRequestDto);

        request.setCreatedAt(LocalDateTime.now());
        request.setRequestStatus(RequestStatus.PENDING);

        return requestMapper.toRequestDto(requestRepository.save(request));
    }

    public RequestDto setOrUpdateVolunteer(Long volunteerId, Long requestId) {
        Request request = requestRepository.findById(requestId)
                .orElseThrow(RuntimeException::new);

        if(RequestStatus.CLOSED.equals(request.getRequestStatus())) {
            throw new RuntimeException(String.format("Request with id %s already closed", requestId));
        }

        //TODO создать метод проверяющий существует ли волонтёр
        if (false) {
            throw new RuntimeException(String.format("No such user with id: %s", volunteerId));
        }

        request.setVolunteerId(volunteerId);
        request.setRequestStatus(RequestStatus.IN_PROGRESS);

        return requestMapper.toRequestDto(requestRepository.save(request));
    }

    public RequestDto updateRequest(UpdateRequestDto updateRequestDto) {
        Request request = requestRepository.findById(updateRequestDto.getId())
                .orElseThrow(() -> new RuntimeException(String.format("Не найдена заявка с id: %s", updateRequestDto.getId())));

        if (updateRequestDto.getDescription() != null) {
            request.setDescription(updateRequestDto.getDescription());
        }

        if (updateRequestDto.getAddress() != null) {
            request.setAddress(updateRequestDto.getAddress());
        }

        if (updateRequestDto.getExecutionDateTime() != null) {
            request.setExecutionDateTime(updateRequestDto.getExecutionDateTime());
        }

        return requestMapper.toRequestDto(requestRepository.save(request));
    }

    public RequestDto completeRequest(Long requestId) {
        Request request = requestRepository.findById(requestId)
                .orElseThrow(() -> new RuntimeException(String.format("Не найдена заявка с id: %s", requestId)));

        request.setRequestStatus(RequestStatus.CLOSED);
        request.setExecutedDateTime(LocalDateTime.now());

        return requestMapper.toRequestDto(requestRepository.save(request));
    }

    public List<RequestDto> findAllByNeedingId(Long needingId) {
        //TODO создать метод проверяющий существует ли волонтёр
        if (false) {
            throw new RuntimeException(String.format("No such user with id: %s", needingId));
        }

        return requestRepository.findByNeedingId(needingId).stream()
                .map(requestMapper::toRequestDto)
                .toList();
    }

    public List<RequestDto> findAllByVolunteerId(Long volunteerId) {
        //TODO создать метод проверяющий существует ли волонтёр
        if (false) {
            throw new RuntimeException(String.format("No such user with id: %s", volunteerId));
        }

        return requestRepository.findByVolunteerId(volunteerId).stream()
                .map(requestMapper::toRequestDto)
                .toList();
    }

    public List<RequestDto> findAllByStatus(RequestStatus requestStatus) {
        return requestRepository.findByRequestStatus(requestStatus).stream()
                .map(requestMapper::toRequestDto)
                .toList();
    }

    public List<RequestDto> findAllByType(RequestType requestType) {
        return requestRepository.findByType(requestType).stream()
                .map(requestMapper::toRequestDto)
                .toList();
    }

    public void deleteRequest(Long requestId) {
        requestRepository.deleteById(requestId);
    }

    public List<RequestDto> getAllActive(Pageable pageable) {
        return requestRepository.findByRequestStatusIn(List.of(RequestStatus.PENDING), pageable).stream()
                .map(requestMapper::toRequestDto)
                .toList();
    }

    public Page<Request> getAll(Pageable pageable) {
        return requestRepository.findAll(pageable);
    }
}
