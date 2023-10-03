package backend.siptis.model.entity.projectManagement;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    @Size(max = 1000)
    private String descriptionPhaseShort;
    @Size(max = 5000)
    private String descriptionPhaseLong;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "modality_id")
    @JsonManagedReference
    private Modality modality;

    @OneToMany(mappedBy = "phase")
    @JsonBackReference
    private Collection<DocumentPhase> documents;
}
