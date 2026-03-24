package by.tami.kinotower.film.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Set;

@Getter
@Setter
public class FilmRequestDto {
    @NotBlank(message = "Film name cannot be empty")
    @Size(max = 255, message = "Film name must be under 255 characters")
    private String name;

    private String description;

    @NotBlank(message = "Please specify the movie language")
    private String language;

    @NotNull(message = "Age rating is required")
    @Min(value = 0, message = "Age rating cannot be negative")
    @Max(value = 21, message = "Age rating cannot exceed 21")
    private Integer ageRating;

    @NotNull(message = "Base price must be specified")
    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than zero")
    private BigDecimal basePrice;

    @NotNull(message = "Film duration is required")
    @Positive(message = "Duration must be a positive number of minutes")
    private Integer durationMin;

    private Long previewImageId;

    @NotNull(message = "Please provide a video file ID")
    private Long filmVideoId;

    @NotEmpty(message = "A film must have at least one genre assigned")
    private Set<Long> genreIds;
}
