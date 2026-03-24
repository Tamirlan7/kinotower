package by.tami.kinotower.film.model;

import by.tami.kinotower.file.model.File;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "t_film")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false, length = 50)
    private String language = "Қазақша (Дубляж)";

    @Column(name = "age_rating", nullable = false)
    private Integer ageRating = 18;

    @Column(name = "base_price", nullable = false, precision = 10, scale = 2)
    private BigDecimal basePrice;

    @Column(name = "duration_min", nullable = false)
    private Integer durationMin;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "preview_image_id")
    private File previewImage;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "film_video_id", nullable = false)
    private File filmVideo;
}
