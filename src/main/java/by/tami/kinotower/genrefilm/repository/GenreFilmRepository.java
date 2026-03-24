package by.tami.kinotower.genrefilm.repository;

import by.tami.kinotower.genrefilm.model.GenreFilm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreFilmRepository extends JpaRepository<GenreFilm, Long> {
}
