package ru.lutsenko.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.lutsenko.user.dto.feedback.CreateFeedbackDto;
import ru.lutsenko.user.dto.feedback.FeedbackDto;
import ru.lutsenko.user.service.FeedbackService;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/feedback")
@RequiredArgsConstructor
public class FeedbackController {

    private final FeedbackService feedbackService;

    @PostMapping
    public ResponseEntity<FeedbackDto> sendFeedback(@RequestBody CreateFeedbackDto createFeedbackDto) {
        FeedbackDto feedbackDto = feedbackService.sendFeedback(createFeedbackDto);
        return ResponseEntity.ok(feedbackDto);
    }


    @GetMapping("/avg/{id}")
    public ResponseEntity<Double> avgMarkById(@PathVariable Long id) {
        Double resultDouble = feedbackService.avgMarkById(id);
        return ResponseEntity.ok(resultDouble);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<FeedbackDto>> findAllByReceiverId(@PathVariable Long id) {
        List<FeedbackDto> feedbackDtos = feedbackService.findAllByReceiverId(id);
        return ResponseEntity.ok(feedbackDtos);
    }

    @GetMapping("/exists")
    public Boolean existsByRequestId(@RequestParam Long requestId) {
        return feedbackService.existsByRequestId(requestId);
    }
}
