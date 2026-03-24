package by.tami.kinotower.filmcast.model;

import by.tami.kinotower.castperson.model.CastPerson;
import by.tami.kinotower.film.model.Film;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name = "t_film_cast")
@IdClass(FilmCastId.class) // Tells JPA to use our helper class for the PK
@Getter
@Setter
public class FilmCast {
    @Id
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "film_id", nullable = false)
    private Film film;

    @Id
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cast_person_id", nullable = false)
    private CastPerson castPerson;

    @ColumnDefault("'Actor'")
    @Column(name = "role_name", length = 100)
    private String roleName;
}
