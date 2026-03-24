package by.tami.kinotower.film.controller;

import by.tami.kinotower.film.dto.FilmRequestDto;
import by.tami.kinotower.film.dto.FilmResponseDto;
import by.tami.kinotower.film.model.Film;
import by.tami.kinotower.film.service.FilmService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/films")
@RequiredArgsConstructor
public class FilmController {

    private final FilmService filmService;

    @PostMapping
    public ResponseEntity<FilmResponseDto> createFilm(@RequestBody FilmRequestDto body) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(filmService.createFilm(body));
    }

}
