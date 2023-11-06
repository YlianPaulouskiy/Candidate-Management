package by.krainet.management.persistence.model;

import by.krainet.management.persistence.converter.DateConverter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "results")
public class Result {

    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Convert(converter = DateConverter.class)
    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private Double score;

    @ManyToOne
    private CandidateTest candidateTest;

}
