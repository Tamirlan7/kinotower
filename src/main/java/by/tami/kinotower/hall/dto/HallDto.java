package by.tami.kinotower.hall.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HallDto {
    private Long id;
    private String name;
    private Integer seatsPerRow;
    private Integer rowsCount;
    private Boolean is3d;
}
