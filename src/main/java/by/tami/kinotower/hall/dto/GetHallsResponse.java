package by.tami.kinotower.hall.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GetHallsResponse {
    private List<HallDto> data;
    private long totalPages;
    private long totalItems;
}
