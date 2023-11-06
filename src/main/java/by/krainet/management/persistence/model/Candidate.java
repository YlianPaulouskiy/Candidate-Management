package by.krainet.management.persistence.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@EqualsAndHashCode(of = {"lastname", "name", "middleName"})
@Entity
@Table(name = "candidates")
public class Candidate {

    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 40)
    private String lastname;

    @Column(nullable = false, length = 40)
    private String name;

    @Column(name = "middle_name", nullable = false, length = 40)
    private String middleName;

    @Column(name = "photo_path")
    private String photo;

    private String description;

    @Column(name = "cv_path")
    private String cv;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "candidate_id")
    private List<Direction> directions = new ArrayList<>();

}
