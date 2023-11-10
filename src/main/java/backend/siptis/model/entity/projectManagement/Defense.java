package backend.siptis.model.entity.projectManagement;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "defense")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Defense {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "place_to_defense_id")
    @JsonBackReference
    private PlaceToDefense placeToDefense;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    @JsonBackReference
    private Project project;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private String substituteName;

    public Defense(PlaceToDefense placeToDefense, Project project, LocalDate date, LocalTime startTime, LocalTime endTime, String substituteName) {
        this.placeToDefense = placeToDefense;
        this.project = project;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.substituteName = substituteName;
    }
}
