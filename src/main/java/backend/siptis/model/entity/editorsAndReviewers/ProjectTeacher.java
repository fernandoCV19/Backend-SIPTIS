package backend.siptis.model.entity.editorsAndReviewers;

import backend.siptis.auth.entity.SiptisUser;
import backend.siptis.model.entity.projectManagement.Project;
import backend.siptis.utils.constant.entityConstants.EditorsAndReviewersConstants.ProjectTeacherTable;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = ProjectTeacherTable.NAME)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectTeacher {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = ProjectTeacherTable.Id.NAME,
            nullable = ProjectTeacherTable.Id.NULLABLE,
            unique = ProjectTeacherTable.Id.UNIQUE)
    private Long id;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = ProjectTeacherTable.JoinTeacher.NAME,
            nullable = ProjectTeacherTable.JoinTeacher.NULLABLE)
    private SiptisUser teacher;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = ProjectTeacherTable.JoinProject.NAME,
            nullable = ProjectTeacherTable.JoinProject.NULLABLE)
    private Project project;

    @Column(name = ProjectTeacherTable.Accepted.NAME)
    private Boolean accepted;

    @Column(name = ProjectTeacherTable.Reviewed.NAME)
    private Boolean reviewed;
}
