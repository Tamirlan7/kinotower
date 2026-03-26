package by.tami.kinotower.film.controller;

import by.tami.kinotower.film.dto.FilmRequestDto;
import by.tami.kinotower.film.dto.FilmDto;
import by.tami.kinotower.film.dto.GetFilmsParams;
import by.tami.kinotower.film.dto.GetFilmsResponse;
import by.tami.kinotower.film.service.FilmService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/films")
@RequiredArgsConstructor
public class FilmController {

    private final FilmService filmService;

    @GetMapping
    public ResponseEntity<GetFilmsResponse> getAllFilms(
            @ModelAttribute GetFilmsParams params
    ) {
        return ResponseEntity
                .ok(filmService.getAllFilms(params));
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<FilmDto> createFilm(
            @Valid @ModelAttribute FilmRequestDto body
    ) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(filmService.createFilm(body));
    }

}
