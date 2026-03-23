package by.tami.kinotower.genre.model;

import jakarta.persistence.*;

@Entity
@Table(name = "t_genre")
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String name;
}
