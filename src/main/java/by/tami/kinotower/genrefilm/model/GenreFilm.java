package by.tami.kinotower.genrefilm.model;

import by.tami.kinotower.film.model.Film;
import by.tami.kinotower.genre.model.Genre;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "t_genre_film")
@IdClass(GenreFilmId.class) // Tells JPA to use our helper class for the PK
@Getter
@Setter
public class GenreFilm {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "film_id")
    private Film film;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "genre_id")
    private Genre genre;
}