package by.tami.kinotower.film.mapper;

import by.tami.kinotower.file.mapper.FileMapper;
import by.tami.kinotower.film.dto.FilmDto;
import by.tami.kinotower.film.model.Film;
import by.tami.kinotower.genre.dto.GenreDto;

import java.util.Set;

public class FilmMapper {
    public static FilmDto toDto(Film film) {
        FilmDto dto = new FilmDto();
        dto.setName(film.getName());
        dto.setDescription(film.getDescription());
        dto.setLanguage(film.getLanguage());
        dto.setAgeRating(film.getAgeRating());
        dto.setBasePrice(film.getBasePrice());
        dto.setDurationMin(film.getDurationMin());
        dto.setPreviewImage(FileMapper.toDto(film.getPreviewImage()));
        dto.setFilmVideo(FileMapper.toDto(film.getFilmVideo()));
        return dto;
    }

    public static FilmDto toDto(Film film, Set<GenreDto> genres) {
        var dto = toDto(film);
        dto.setGenres(genres);
        return dto;
    }
}
