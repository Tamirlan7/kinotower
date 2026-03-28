package by.tami.kinotower.screening.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CreateScreeningRequest {
    @NotNull(message = "filmId is required")
    private long filmId;

    @NotNull(message = "hallId is required")
    private long hallId;

    @NotNull(message = "screeningDateTime is required")
    @Future(message = "screeningDateTime must be in the future")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime screeningDateTime;
}
