package ru.lutsenko.request;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.lutsenko.request.dto.CreateRequestDto;
import ru.lutsenko.request.dto.RequestDto;
import ru.lutsenko.request.entity.Address;
import ru.lutsenko.request.entity.RequestType;
import ru.lutsenko.request.service.RequestAndNotificationFacade;
import ru.lutsenko.request.service.RequestService;

import java.time.LocalDateTime;

@Slf4j
@SpringBootApplication
public class RequestServiceApplication implements CommandLineRunner {
	@Autowired
	RequestAndNotificationFacade requestService;
	public static void main(String[] args) {
		SpringApplication.run(RequestServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		CreateRequestDto createRequestDto = new CreateRequestDto(RequestType.BABUSHKA,
				"Описание заявки",
				Address.builder()
						.city("Саратов")
						.build(),
				LocalDateTime.now().plusWeeks(2),
				10L);

		RequestDto request = requestService.createRequest(createRequestDto);


		request = requestService.setOrUpdateVolunteer(11L, request.getId());


		request = requestService.completeRequest(request.getId());

	}
}
