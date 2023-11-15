package backend.siptis.model.entity.editorsAndReviewers;

import backend.siptis.auth.entity.SiptisUser;
import backend.siptis.model.entity.projectManagement.Project;
import backend.siptis.utils.constant.entityConstants.EditorsAndReviewersConstants.ProjectTutorTable;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = ProjectTutorTable.NAME)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectTutor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = ProjectTutorTable.Id.NAME,
            nullable = ProjectTutorTable.Id.NULLABLE,
            unique = ProjectTutorTable.Id.UNIQUE)
    private Long id;

    @ManyToOne
    @JoinColumn(name = ProjectTutorTable.JoinTutor.NAME,
            nullable = ProjectTutorTable.JoinTutor.NULLABLE)
    @JsonBackReference
    private SiptisUser tutor;

    @ManyToOne
    @JoinColumn(name = ProjectTutorTable.JoinProject.NAME,
            nullable = ProjectTutorTable.JoinProject.NULLABLE)
    @JsonBackReference
    private Project project;

    @Column(name = ProjectTutorTable.Accepted.NAME)
    private Boolean accepted;

    @Column(name = ProjectTutorTable.Reviewed.NAME)
    private Boolean reviewed;
}
