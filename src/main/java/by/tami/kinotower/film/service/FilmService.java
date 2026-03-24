package by.tami.kinotower.film.service;

import by.tami.kinotower.film.dto.FilmRequestDto;
import by.tami.kinotower.film.dto.FilmResponseDto;
import by.tami.kinotower.film.repository.FilmRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FilmService {

    private final FilmRepository filmRepository;

    public FilmResponseDto createFilm(FilmRequestDto body) {
        return null;
    }
}
