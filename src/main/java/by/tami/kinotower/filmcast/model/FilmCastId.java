package by.tami.kinotower.filmcast.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class FilmCastId implements Serializable {
    private Long film;
    private Long castPerson;
}
