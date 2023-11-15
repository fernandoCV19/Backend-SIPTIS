package backend.siptis.model.entity.phaseManagement;

import backend.siptis.model.entity.projectManagement.Modality;
import backend.siptis.utils.constant.entityConstants.PhaseManagementConstants.PhaseTable;
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
@Table(name = PhaseTable.NAME)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Phase {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = PhaseTable.Id.NAME,
            nullable = PhaseTable.Id.NULLABLE,
            unique = PhaseTable.Id.UNIQUE)
    private Long id;

    @Column(name = PhaseTable.Name.NAME)
    private String name;

    @Size(max = PhaseTable.DescriptionPhaseSort.MAX_SIZE)
    @Column(name = PhaseTable.DescriptionPhaseSort.NAME)
    private String descriptionPhaseShort;

    @Size(max = PhaseTable.DescriptionPhaseLong.MAX_SIZE)
    @Column(name = PhaseTable.DescriptionPhaseLong.NAME)
    private String descriptionPhaseLong;

    @Column(name = PhaseTable.NumberPhase.NAME)
    private int numberPhase;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = PhaseTable.JoinModality.NAME)
    @JsonManagedReference
    private Modality modality;

    @OneToMany(mappedBy = PhaseTable.MappedDocuments.NAME)
    @JsonBackReference
    private Collection<DocumentPhase> documents;
}
