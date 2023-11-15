package backend.siptis.model.entity.editorsAndReviewers;

import backend.siptis.auth.entity.SiptisUser;
import backend.siptis.model.entity.projectManagement.Project;
import backend.siptis.utils.constant.entityConstants.EditorsAndReviewersConstants.ProjectTribunalTable;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = ProjectTribunalTable.NAME)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectTribunal {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = ProjectTribunalTable.Id.NAME,
            nullable = ProjectTribunalTable.Id.NULLABLE,
            unique = ProjectTribunalTable.Id.UNIQUE)
    private Long id;

    @ManyToOne
    @JoinColumn(name = ProjectTribunalTable.JoinTribunal.NAME,
            nullable = ProjectTribunalTable.JoinTribunal.NULLABLE)
    @JsonBackReference
    private SiptisUser tribunal;

    @ManyToOne
    @JoinColumn(name = ProjectTribunalTable.JoinProject.NAME,
            nullable = ProjectTribunalTable.JoinProject.NULLABLE)
    @JsonBackReference
    private Project project;

    @Column(name = ProjectTribunalTable.Accepted.NAME)
    private Boolean accepted;

    @Column(name = ProjectTribunalTable.DefensePoints.NAME)
    private Double defensePoints;

    @Column(name = ProjectTribunalTable.Reviewed.NAME)
    private Boolean reviewed;

    public ProjectTribunal(SiptisUser tribunal, Project project) {
        this.tribunal = tribunal;
        this.project = project;
        accepted = false;
        reviewed = false;
        defensePoints = null;
    }
}
