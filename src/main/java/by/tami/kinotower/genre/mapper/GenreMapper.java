package by.tami.kinotower.genre.mapper;

import by.tami.kinotower.genre.dto.GenreDto;
import by.tami.kinotower.genre.model.Genre;

public class GenreMapper {

    public static GenreDto toDto(Genre genre) {
        var dto =  new GenreDto();
        dto.setId(genre.getId());
        dto.setName(genre.getName());
        return dto;
    }
}
