package backend.siptis.model.entity.records;

import backend.siptis.model.entity.projectManagement.Project;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "actividad")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "activity_description")
    private String activityDescription;
    @Column(name = "activity_date")
    private Date activityDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "proyecto_grado_id", nullable = false)
    @JsonBackReference
    private Project project;
}
