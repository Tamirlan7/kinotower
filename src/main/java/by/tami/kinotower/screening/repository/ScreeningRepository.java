package by.tami.kinotower.screening.repository;

import by.tami.kinotower.screening.model.Screening;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ScreeningRepository extends JpaRepository<Screening, Integer> {

    @Query("""
                SELECT COUNT(s) > 0 FROM Screening s
                JOIN Film f ON f.id = s.film.id
                JOIN Hall h ON s.hall.id = h.id
                WHERE h.id = :hallId AND :startTime < FUNCTION('TIMESTAMP_ADD_MINTES', s.screeningDateTime, s.film.durationMin)
                AND :endTime < s.screeningDateTime
            """)
    boolean existsOverlappingScreenings();

}
