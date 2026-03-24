package by.tami.kinotower.genrefilm.service;

import by.tami.kinotower.film.model.Film;
import by.tami.kinotower.genre.model.Genre;
import by.tami.kinotower.genre.service.GenreService;
import by.tami.kinotower.genrefilm.model.GenreFilm;
import by.tami.kinotower.genrefilm.repository.GenreFilmRepository;
import by.tami.kinotower.web.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class GenreFilmService {

    private final GenreService genreService;
    private final GenreFilmRepository genreFilmRepository;

    private void saveGenresForFilm(Film film, Collection<Long> genreIds) {
        Set<GenreFilm> genreFilms = new HashSet<>();
        List<Genre> genres = genreService.findGenresByIds(genreIds);

        if (genres.size() != genreIds.size()) {
            throw new NotFoundException("Genre Ids Not Found");
        }

        for (Long genreId : genreIds) {
            var genreFilm = new GenreFilm();
            Genre genre = genres.stream()
                    .filter((g) -> g.getId().equals(genreId))
                    .findFirst()
                    .get();

            genreFilm.setFilm(film);
            genreFilm.setGenre(genre);
            genreFilms.add(genreFilm);
        }

        genreFilmRepository.saveAll(genreFilms);
    }

}
