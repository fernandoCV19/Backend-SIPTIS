package backend.siptis.model.entity.editorsAndReviewers;

import backend.siptis.auth.entity.SiptisUser;
import backend.siptis.model.entity.projectManagement.Project;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "project_student")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ProjectStudent {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private SiptisUser student;

    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;
}
