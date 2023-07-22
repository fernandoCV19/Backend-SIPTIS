package backend.siptis.model.entity.projectManagement;

import backend.siptis.model.entity.userData.Document;
import com.fasterxml.jackson.annotation.JsonBackReference;
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
