package backend.siptis.model.entity.projectManagement;

import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;

@Entity
@Table(name = "sub_area")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SubArea {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    private String name;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "subAreas")
    private Collection<Project> proyectosDeGrado;
}
