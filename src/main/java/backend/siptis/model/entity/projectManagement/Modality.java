package backend.siptis.model.entity.projectManagement;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;

@Entity
@Table(name = "modality")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Modality {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "modality", fetch = FetchType.LAZY )
    @JsonManagedReference
    private Collection<Project> projects;

}
