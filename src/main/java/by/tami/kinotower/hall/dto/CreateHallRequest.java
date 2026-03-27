package by.tami.kinotower.hall.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateHallRequest {
    @Size(max = 255, message = "the length of name cannot exceed 255 characters")
    @NotBlank(message = "name is required")
    private String name;

    @Positive(message = "rowsCount cannot be less than 1")
    @NotNull(message = "rowsCount is required")
    private Integer rowsCount;

    @Positive(message = "seatsPerRow cannot be less than 1")
    @NotNull(message = "seatsPerRow is required")
    private Integer seatsPerRow;

    @JsonProperty("is3d")
    private boolean is3d = false;
}
