package ru.lutsenlo.statistic.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.lutsenlo.statistic.service.StatisticsService;

import java.time.LocalDate;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/statistics")
@RequiredArgsConstructor
public class StatisticsController {

    private final StatisticsService statisticsService;

    @GetMapping("/avg/{userId}")
    public Double avgMarkByUserId(@PathVariable Long userId) {
        return statisticsService.avgMarkByUserId(userId);
    }

    @GetMapping("/requests/{userId}")
    public Map<LocalDate, Long> requestsByUserId(@PathVariable Long userId) {
        return statisticsService.requestsByUserId(userId);
    }

    @GetMapping("/chart/{userId}")
    public Map<String, Long> chartByUserId(@PathVariable Long userId) {
        return statisticsService.chartByUserId(userId);
    }
}
