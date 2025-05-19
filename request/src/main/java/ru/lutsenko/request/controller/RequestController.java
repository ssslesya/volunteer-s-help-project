package ru.lutsenko.request.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.lutsenko.request.dto.CreateRequestDto;
import ru.lutsenko.request.dto.RequestDto;
import ru.lutsenko.request.dto.UpdateRequestDto;
import ru.lutsenko.request.entity.Request;
import ru.lutsenko.request.entity.RequestStatus;
import ru.lutsenko.request.entity.RequestType;
import ru.lutsenko.request.service.RequestAndNotificationFacade;
import ru.lutsenko.request.service.RequestService;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/request")
@RequiredArgsConstructor
public class RequestController {
    private final RequestService requestService;
    private final RequestAndNotificationFacade requestAndNotificationFacade;

    @PutMapping("/complete/{requestId}")
    public ResponseEntity<RequestDto> completeRequest(@PathVariable Long requestId) {
        RequestDto requestDto = requestAndNotificationFacade.completeRequest(requestId);
        return ResponseEntity.ok(requestDto);
    }

    @PostMapping
    public ResponseEntity<RequestDto> createRequest(@RequestBody CreateRequestDto createRequestDto) {
        RequestDto requestDto = requestAndNotificationFacade.createRequest(createRequestDto);
        return ResponseEntity.ok(requestDto);
    }

    @PutMapping("/setVolunteer")
    public ResponseEntity<RequestDto> setOrUpdateVolunteer(@RequestParam Long volunteerId, @RequestParam Long requestId) {
        RequestDto requestDto = requestAndNotificationFacade.setOrUpdateVolunteer(volunteerId, requestId);
        return ResponseEntity.ok(requestDto);
    }

    @PutMapping
    public ResponseEntity<RequestDto> updateRequest(@RequestBody UpdateRequestDto updateRequestDto) {
        RequestDto requestDto = requestAndNotificationFacade.updateRequest(updateRequestDto);
        return ResponseEntity.ok(requestDto);
    }

    @GetMapping("/needing/{needingId}")
    public ResponseEntity<List<RequestDto>> findAllByNeedingId(@PathVariable Long needingId) {
        List<RequestDto> requestDtos = requestService.findAllByNeedingId(needingId);
        return ResponseEntity.ok(requestDtos);
    }

    @GetMapping("/volunteer/{volunteerId}")
    public ResponseEntity<List<RequestDto>> findAllByVolunteerId(@PathVariable Long volunteerId) {
        List<RequestDto> requestDtos = requestService.findAllByVolunteerId(volunteerId);
        return ResponseEntity.ok(requestDtos);
    }

    @GetMapping("/status/{requestStatus}")
    public ResponseEntity<List<RequestDto>> findAllByStatus(@PathVariable RequestStatus requestStatus) {
        List<RequestDto> requestDtos = requestService.findAllByStatus(requestStatus);
        return ResponseEntity.ok(requestDtos);
    }

    @GetMapping("/type/{requestType}")
    public ResponseEntity<List<RequestDto>> findAllByType(@PathVariable RequestType requestType) {
        List<RequestDto> requestDtos = requestService.findAllByType(requestType);
        return ResponseEntity.ok(requestDtos);
    }

    @DeleteMapping("/{requestId}")
    public ResponseEntity<Void> deleteRequest(@PathVariable Long requestId) {
        requestService.deleteRequest(requestId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RequestDto> findById(@PathVariable Long id) {
        RequestDto requestDto = requestService.findById(id);
        return ResponseEntity.ok(requestDto);
    }

    @GetMapping("/active")
    public List<RequestDto> getAllActive(Pageable pageable) {
        return requestService.getAllActive(pageable);
    }


    @GetMapping
    public PagedModel<Request> getAll(Pageable pageable) {
        Page<Request> requests = requestService.getAll(pageable);
        return new PagedModel<>(requests);
    }
}

