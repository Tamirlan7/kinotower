package by.tami.kinotower.genrefilm.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode // Important: JPA uses this to compare keys
public class GenreFilmId implements Serializable {
    private Long film;  // Must match the field name in the Entity
    private Long genre; // Must match the field name in the Entity
}
