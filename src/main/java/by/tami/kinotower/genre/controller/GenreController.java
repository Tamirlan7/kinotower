package by.tami.kinotower.genre.controller;

import by.tami.kinotower.genre.dto.GenreDto;
import by.tami.kinotower.genre.dto.GetGenresParams;
import by.tami.kinotower.genre.dto.GetGenresResponse;
import by.tami.kinotower.genre.service.GenreService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/genres")
@RequiredArgsConstructor
public class GenreController {

    private final GenreService genreService;

    @GetMapping
    public ResponseEntity<GetGenresResponse> getAllGenres(
            @ModelAttribute @Valid GetGenresParams params
    ) {
        return ResponseEntity.ok(genreService.getGenres(params));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenreDto> getAllGenres(@PathVariable Long id) {
        return ResponseEntity.ok(genreService.getGenreById(id));
    }
}
