package ru.lutsenlo.statistic.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.lutsenlo.statistic.feign.dto.RequestDto;

import java.util.List;

@FeignClient(name = "Request", url = "http://localhost:8080", path = "/request")
public interface RequestFeign {

    @GetMapping("/volunteer/{volunteerId}")
    List<RequestDto> findAllByVolunteerId(@PathVariable Long volunteerId);
}
