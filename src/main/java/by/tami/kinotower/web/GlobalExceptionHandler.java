package by.tami.kinotower.web;

import by.tami.kinotower.file.exception.IllegalFileContentTypeException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalFileContentTypeException.class)
    public ResponseEntity<Map<String, String>>  handleIllegalFileContentTypeException(IllegalFileContentTypeException ex) {
        return ResponseEntity
                .badRequest()
                .body(Map.of("message", ex.getMessage()));
    }
}
