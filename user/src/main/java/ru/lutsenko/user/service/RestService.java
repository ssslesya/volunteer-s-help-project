package ru.lutsenko.user.service;

import lombok.SneakyThrows;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClient;
import org.springframework.web.multipart.MultipartFile;
import ru.lutsenko.user.dto.request.SmallRequestDto;

import java.net.URI;
import java.util.List;
import java.util.Objects;

@Service
public class RestService {
    private final RestClient restClient = RestClient.create();

    public SmallRequestDto getRequestById(Long id) {
        return restClient.get()
                .uri(URI.create(String.format("http://localhost:8080/request/%s", id)))
                .retrieve()
                .onStatus(
                        HttpStatusCode::is5xxServerError,
                        (request, response) -> {
                            throw new IllegalArgumentException(String.format("Не найдена заявка с id: %s", id));
                        })
                .onStatus(HttpStatusCode::is4xxClientError,
                        (request, response) -> {
                            throw new IllegalArgumentException(String.format("Не найдена заявка с id: %s", id));
                        })
                .toEntity(SmallRequestDto.class)
                .getBody();
    }

    @SneakyThrows
    public List<String> saveFiles(List<MultipartFile> files) {
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        for (MultipartFile file : files) {
            Resource resource = new ByteArrayResource(file.getBytes()) {
                @Override
                public String getFilename() {
                    return file.getOriginalFilename();
                }
            };
            body.add("files", resource);
        }

        ResponseEntity<String[]> response = restClient.post()
                .uri("http://localhost:8085/api/files/upload")
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .body(body)
                .retrieve()
                .toEntity(String[].class);

        return List.of(Objects.requireNonNull(response.getBody()));
    }
}
