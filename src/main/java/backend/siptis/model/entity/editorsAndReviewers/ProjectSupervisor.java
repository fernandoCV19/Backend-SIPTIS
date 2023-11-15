package backend.siptis.model.entity.editorsAndReviewers;

import backend.siptis.auth.entity.SiptisUser;
import backend.siptis.model.entity.projectManagement.Project;
import backend.siptis.utils.constant.entityConstants.EditorsAndReviewersConstants.ProjectSupervisorTable;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = ProjectSupervisorTable.NAME)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectSupervisor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = ProjectSupervisorTable.Id.NAME,
            nullable = ProjectSupervisorTable.Id.NULLABLE,
            unique = ProjectSupervisorTable.Id.UNIQUE)
    private Long id;

    @ManyToOne
    @JoinColumn(name = ProjectSupervisorTable.JoinSupervisor.NAME,
            nullable = ProjectSupervisorTable.JoinSupervisor.NULLABLE)
    @JsonBackReference
    private SiptisUser supervisor;

    @ManyToOne
    @JoinColumn(name = ProjectSupervisorTable.JoinProject.NAME,
            nullable = ProjectSupervisorTable.JoinProject.NULLABLE)
    @JsonBackReference
    private Project project;

    @Column(name = ProjectSupervisorTable.Accepted.NAME)
    private Boolean accepted;

    @Column(name = ProjectSupervisorTable.Reviewed.NAME)
    private Boolean reviewed;
}
