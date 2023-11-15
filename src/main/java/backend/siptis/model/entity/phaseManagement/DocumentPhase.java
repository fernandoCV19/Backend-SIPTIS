package backend.siptis.model.entity.phaseManagement;

import backend.siptis.utils.constant.entityConstants.PhaseManagementConstants.DocumentPhaseTable;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = DocumentPhaseTable.NAME)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DocumentPhase {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = DocumentPhaseTable.Id.NAME,
            nullable = DocumentPhaseTable.Id.NULLABLE,
            unique = DocumentPhaseTable.Id.UNIQUE)
    private Long id;

    @Column(name = DocumentPhaseTable.Path.NAME)
    private String path;

    @Column(name = DocumentPhaseTable.Name.NAME)
    private String name;

    @Column(name = DocumentPhaseTable.Description.NAME)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = DocumentPhaseTable.JoinPhase.NAME,
            nullable = DocumentPhaseTable.JoinPhase.NULLABLE)
    @JsonBackReference
    private Phase phase;


}
