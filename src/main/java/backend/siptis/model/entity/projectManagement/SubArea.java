package backend.siptis.model.entity.projectManagement;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;

@Entity
@Table(name = "sub_area")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SubArea {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "subAreas")
    @JsonBackReference
    private Collection<Project> projects;
}
