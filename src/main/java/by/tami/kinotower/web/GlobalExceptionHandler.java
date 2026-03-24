package by.tami.kinotower.web;

import by.tami.kinotower.file.exception.IllegalFileContentTypeException;
import by.tami.kinotower.web.exception.BadRequestException;
import by.tami.kinotower.web.exception.NotFoundException;
import org.springframework.http.HttpStatus;
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

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Map<String, String>>  handleBadRequestException(IllegalFileContentTypeException ex) {
        return ResponseEntity
                .badRequest()
                .body(Map.of("message", ex.getMessage()));
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Map<String, String>>  handleNotFoundException(IllegalFileContentTypeException ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(Map.of("message", ex.getMessage()));
    }
}
