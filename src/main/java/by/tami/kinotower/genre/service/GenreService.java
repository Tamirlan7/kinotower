package by.tami.kinotower.genre.service;

import by.tami.kinotower.genre.model.Genre;
import by.tami.kinotower.genre.repository.GenreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GenreService {

    private final GenreRepository genreRepository;

    public List<Genre> findGenresByIds(Iterable<Long> genreIds) {
        return genreRepository.findAllById(genreIds);
    }
}
