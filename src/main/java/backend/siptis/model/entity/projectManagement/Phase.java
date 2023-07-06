package backend.siptis.model.entity.projectManagement;

import backend.siptis.model.entity.userData.Document;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;

@Entity
@Table(name = "phase")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Phase {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "phase", fetch = FetchType.LAZY)
    @JsonManagedReference
    private Collection<DocumentPhase> documents;
}
