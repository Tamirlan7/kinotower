package by.tami.kinotower.film.repository;

import by.tami.kinotower.film.model.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FilmRepository extends JpaRepository<Film, Long> {
    Optional<Film> findByName(String name);

    @Query("""
                 SELECT f FROM Film f
                 LEFT JOIN FETCH File image ON image.id = f.previewImage.id
                 LEFT JOIN FETCH File video ON video.id = f.filmVideo.id
                 WHERE f.name ILIKE CONCAT('%', :name ,'%') AND f.id > :cursorId
                 ORDER BY f.id ASC
                 LIMIT :limit
            """)
    List<Film> findAllFiltered(
            @Param("name") String name,
            @Param("cursorId") Long cursorId,
            @Param("limit") Integer limit
    );
}
