package by.tami.kinotower.film.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GetFilmsResponse {
    private List<FilmDto> data;
    private String nextCursor;
    private boolean hasNext;
}
