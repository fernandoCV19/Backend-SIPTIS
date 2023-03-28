package backend.siptis.model.entity.projectManagement;

import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;

@Entity
@Table(name = "project_area")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ProjectArea {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    private String name;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "areas")
    private Collection<Project> projects;
}
