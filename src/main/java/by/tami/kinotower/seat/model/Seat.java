package by.tami.kinotower.seat.model;

import by.tami.kinotower.screening.model.Screening;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(
        name = "t_seat",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {
                        "screening_id",
                        "row_number",
                        "seat_number"
                })
        }
)
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "screening_id", nullable = false)
    private Screening screening;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @Column(name = "seat_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private SeatType seatType = SeatType.Standard;

    @Column(name = "row_number", length = 10, nullable = false)
    private String rowNumber;

    @Column(name = "seat_number", nullable = false)
    private Short seatNumber;

    @Column(name = "is_available", nullable = false)
    private boolean isAvailable = true;
}
