package by.tami.kinotower.genre.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GetGenresResponse {
    private List<GenreDto> data;
    private long totalItems;
    private long totalPages;
}
