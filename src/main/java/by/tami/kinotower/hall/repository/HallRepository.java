package by.tami.kinotower.hall.repository;

import by.tami.kinotower.hall.model.Hall;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface HallRepository extends JpaRepository<Hall, Integer> {
    boolean existsByName(String name);

    @Query("""
                SELECT h FROM Hall h
                WHERE h.name ILIKE CONCAT('%', :name, '%') 
                            AND (:is3d IS NULL OR :is3d = h.is3d)
            """)
    Page<Hall> findAllByNameAnd3d(
            @Param("name") String name,
            @Param("is3d") Boolean is3d,
            Pageable pageable
    );
}
