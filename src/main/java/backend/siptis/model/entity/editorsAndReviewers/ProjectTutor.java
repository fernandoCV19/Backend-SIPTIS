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
@Table(name = "project_tutor")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectTutor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    private SiptisUser tutor;

    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    @JsonBackReference
    private Project project;
    private Boolean accepted;
    private Boolean reviewed;
}
