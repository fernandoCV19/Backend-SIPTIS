package backend.siptis.model.entity.projectManagement;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

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
    @JsonManagedReference
    private PlaceToDefense placeToDefense;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    private Project project;

    private Date date;

    public Defense(PlaceToDefense placeToDefense, Project project, Date date) {
        this.placeToDefense = placeToDefense;
        this.project = project;
        this.date = date;
    }
}
