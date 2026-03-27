package by.tami.kinotower.hall.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateHallRequest {
    @Size(max = 255, message = "the length of name cannot exceed 255 characters")
    @NotBlank(message = "name is required")
    private String name;

    @Size(min = 1, message = "rowsCount cannot be less than 1")
    @NotNull(message = "rowsCount is required")
    private Integer rowsCount;

    @Size(min = 1, message = "seatsPerRow cannot be less than 1")
    @NotNull(message = "seatsPerRow is required")
    private Integer seatsPerRow;

    private boolean is3d = false;
}
