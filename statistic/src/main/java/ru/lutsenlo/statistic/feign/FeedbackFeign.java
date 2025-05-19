package ru.lutsenlo.statistic.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "FeedbackFeign", url = "http://localhost:8081", path = "/feedback")
public interface FeedbackFeign {

    @GetMapping("/avg/{id}")
    Double avgMarkById(@PathVariable Long id);
}
