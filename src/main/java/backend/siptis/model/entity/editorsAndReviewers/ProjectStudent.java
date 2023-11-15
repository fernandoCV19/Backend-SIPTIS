package backend.siptis.model.entity.editorsAndReviewers;

import backend.siptis.auth.entity.SiptisUser;
import backend.siptis.model.entity.projectManagement.Project;
import backend.siptis.utils.constant.entityConstants.EditorsAndReviewersConstants.ProjectStudentTable;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = ProjectStudentTable.NAME)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectStudent {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = ProjectStudentTable.Id.NAME,
            nullable = ProjectStudentTable.Id.NULLABLE,
            unique = ProjectStudentTable.Id.UNIQUE)
    private Long id;

    @ManyToOne
    @JoinColumn(name = ProjectStudentTable.JoinStudent.NAME,
            nullable = ProjectStudentTable.JoinStudent.NULLABLE)
    @JsonBackReference
    private SiptisUser student;

    @ManyToOne
    @JoinColumn(name = ProjectStudentTable.JoinProject.NAME,
            nullable = ProjectStudentTable.JoinProject.NULLABLE)
    @JsonBackReference
    private Project project;
}
