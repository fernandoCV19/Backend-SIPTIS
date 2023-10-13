package backend.siptis.model.entity.projectManagement;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
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
    private String descriptionPhase;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "modality_id")
    private Modality modality;
    /*@OneToMany(mappedBy = "phase", fetch = FetchType.LAZY)
    @JsonManagedReference
    private Collection<DocumentPhase> documents;*/
    @OneToMany(mappedBy = "phase")
    @JsonBackReference
    private Collection<DocumentPhase> documents;
}
