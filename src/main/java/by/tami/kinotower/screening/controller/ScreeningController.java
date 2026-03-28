package by.tami.kinotower.screening.controller;

import by.tami.kinotower.screening.dto.CreateScreeningRequest;
import by.tami.kinotower.screening.dto.ScreeningDto;
import by.tami.kinotower.screening.model.Screening;
import by.tami.kinotower.screening.service.ScreeningService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/seeing")
public class ScreeningController {

    private ScreeningService screeningService;

    @PostMapping
    public ResponseEntity<ScreeningDto> createScreening(
            @RequestBody @Valid CreateScreeningRequest request
    ) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(screeningService.createScreening(request));
    }

}
