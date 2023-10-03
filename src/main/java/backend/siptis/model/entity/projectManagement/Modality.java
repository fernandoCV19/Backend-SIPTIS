package backend.siptis.model.entity.projectManagement;

import backend.siptis.auth.entity.SiptisUser;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;
@Data
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

    @OneToMany(mappedBy = "modality")
    @JsonBackReference
    private Collection<Project> projects;

    @OneToMany(mappedBy = "modality")
    @JsonBackReference
    private  Collection<Phase> phases;
}