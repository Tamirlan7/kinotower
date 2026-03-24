package by.tami.kinotower.film.mapper;

import by.tami.kinotower.film.dto.FilmResponseDto;
import by.tami.kinotower.film.model.Film;

public class FilmMapper {
    public static FilmResponseDto toDto(Film film) {
        FilmResponseDto dto = new FilmResponseDto();
        return dto;
    }
}
