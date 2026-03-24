package by.tami.kinotower.film.controller;

import by.tami.kinotower.film.dto.FilmRequestDto;
import by.tami.kinotower.film.dto.FilmResponseDto;
import by.tami.kinotower.film.model.Film;
import by.tami.kinotower.film.service.FilmService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/films")
@RequiredArgsConstructor
public class FilmController {

    private final FilmService filmService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<FilmResponseDto> createFilm(
            @Valid @ModelAttribute FilmRequestDto body
    ) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(filmService.createFilm(body));
    }

}
