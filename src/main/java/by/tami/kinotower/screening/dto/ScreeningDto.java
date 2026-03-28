package by.tami.kinotower.screening.dto;

import by.tami.kinotower.film.dto.FilmDto;
import by.tami.kinotower.hall.dto.HallDto;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ScreeningDto {
    private long id;
    private LocalDateTime screeningDateTime;
    private FilmDto film;
    private HallDto hall;
}
