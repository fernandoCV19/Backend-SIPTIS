package backend.siptis.model.entity.editorsAndReviewers;

import backend.siptis.auth.entity.SiptisUser;
import backend.siptis.model.entity.projectManagement.Project;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "project_tribunal")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectTribunal {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    private SiptisUser tribunal;

    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    @JsonBackReference
    private Project project;

    private Boolean accepted;

    @Column(name = "defense_points")
    private Double defensePoints;
    private Boolean reviewed;

    public ProjectTribunal(SiptisUser tribunal, Project project) {
        this.tribunal = tribunal;
        this.project = project;
        accepted = false;
        reviewed = false;
        defensePoints = null;
    }
}
