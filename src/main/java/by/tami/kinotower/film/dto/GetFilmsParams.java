package by.tami.kinotower.film.dto;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class GetFilmsParams {
    private String name = "";

    @Size(min = 1, message = "size has to be greater than 1")
    @Size(max = 100, message = "size has to be less than 100")
    private Integer size = 10;

    private String cursor;
}
