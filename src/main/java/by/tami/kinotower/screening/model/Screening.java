package by.tami.kinotower.screening.model;

import jakarta.persistence.*;

@Entity
@Table(name = "t_screening")
public class Screening {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long id;
}
