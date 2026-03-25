package by.tami.kinotower.film.dto;

import by.tami.kinotower.file.dto.FileDto;
import by.tami.kinotower.genre.dto.GenreDto;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Set;

@Data
public class FilmResponseDto {
    private Long id;
    private String name;
    private String description;
    private String language;
    private Integer ageRating;
    private BigDecimal basePrice;
    private Integer durationMin;
    private FileDto previewImage;
    private FileDto filmVideo;
    private Set<GenreDto> genres;
}
