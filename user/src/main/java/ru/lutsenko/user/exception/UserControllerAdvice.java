package ru.lutsenko.user.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;

@ControllerAdvice
public class UserControllerAdvice {

    @ExceptionHandler({IllegalAccessException.class, RuntimeException.class})
    public ResponseEntity<Map.Entry> handleException(Exception ex) {
        return ResponseEntity.badRequest()
                .body(Map.entry("Ошибка", ex.getMessage()));
    }
}
