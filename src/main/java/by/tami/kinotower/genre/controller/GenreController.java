package by.tami.kinotower.genre.controller;

import by.tami.kinotower.genre.dto.GenreDto;
import by.tami.kinotower.genre.dto.GetGenresParams;
import by.tami.kinotower.genre.dto.GetGenresResponse;
import by.tami.kinotower.genre.service.GenreService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api/v1/genres")
public class GenreController {

    private final GenreService genreService;

    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @GetMapping
    public ResponseEntity<GetGenresResponse> getAllGenres(
            @ModelAttribute GetGenresParams params
    ) {
        return ResponseEntity.ok(genreService.getGenres(params));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenreDto> getAllGenres(@PathVariable Long id) {
        return ResponseEntity.ok(genreService.getGenreById(id));
    }
}
