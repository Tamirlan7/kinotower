package by.tami.kinotower.film.mapper;

import by.tami.kinotower.file.mapper.FileMapper;
import by.tami.kinotower.film.dto.FilmResponseDto;
import by.tami.kinotower.film.model.Film;
import by.tami.kinotower.genre.dto.GenreDto;

import java.util.Set;

public class FilmMapper {
    public static FilmResponseDto toDto(Film film) {
        FilmResponseDto dto = new FilmResponseDto();
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

    public static FilmResponseDto toDto(Film film, Set<GenreDto> genres) {
        var dto = toDto(film);
        dto.setGenres(genres);
        return dto;
    }
}
