package by.tami.kinotower.genrefilm.service;

import by.tami.kinotower.file.events.FileCleanUpEvent;
import by.tami.kinotower.film.model.Film;
import by.tami.kinotower.genre.dto.GenreDto;
import by.tami.kinotower.genre.mapper.GenreMapper;
import by.tami.kinotower.genre.model.Genre;
import by.tami.kinotower.genre.service.GenreService;
import by.tami.kinotower.genrefilm.model.GenreFilm;
import by.tami.kinotower.genrefilm.repository.GenreFilmRepository;
import by.tami.kinotower.web.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GenreFilmService {

    private final GenreService genreService;
    private final GenreFilmRepository genreFilmRepository;
    private final ApplicationEventPublisher applicationEventPublisher;

    public Set<GenreDto> saveGenresForFilm(Film film, Collection<Long> genreIds) {
        List<Genre> genres = genreService.findGenresByIds(genreIds);

        if (genres.size() != genreIds.size()) {
            applicationEventPublisher.publishEvent(new FileCleanUpEvent(List.of(film.getFilmVideo(), film.getPreviewImage())));
            throw new NotFoundException("Genre Ids Not Found");
        }

        List<GenreFilm> genreFilms = genres.stream()
                .map(genre -> {
                    var genreFilm = new GenreFilm();
                    genreFilm.setFilm(film);
                    genreFilm.setGenre(genre);
                    return genreFilm;
                })
                .toList();


        genreFilmRepository.saveAll(genreFilms);
        return genres.stream().map(GenreMapper::toDto).collect(Collectors.toSet());
    }

}
