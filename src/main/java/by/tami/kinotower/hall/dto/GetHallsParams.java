package by.tami.kinotower.hall.dto;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetHallsParams {
    @Size(max = 255, message = "name cannot exceed 255 characters")
    private String name = "";
    private Boolean is3d = null;

    @PositiveOrZero(message = "page has to be equal to 0 or any positive number")
    private int page = 0;

    @Positive(message = "size has to be greater than or equal to 1")
    private int size = 10;
}
