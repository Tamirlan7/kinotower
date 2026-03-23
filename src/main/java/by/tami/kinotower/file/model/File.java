package by.tami.kinotower.file.model;

import jakarta.persistence.*;

@Entity
@Table(name = "t_file")
public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String path;

    @Column(nullable = false, length = 100)
    private String type;
}
