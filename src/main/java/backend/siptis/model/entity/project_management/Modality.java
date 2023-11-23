package backend.siptis.model.entity.project_management;

import backend.siptis.model.entity.phase_management.Phase;
import backend.siptis.utils.constant.entity_constants.ProjectManagementConstants.ModalityTable;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;

@Entity
@Table(name = ModalityTable.NAME)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Modality {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = ModalityTable.Id.NAME,
            nullable = ModalityTable.Id.NULLABLE,
            unique = ModalityTable.Id.UNIQUE)
    private Long id;

    @Column(name = ModalityTable.Name.NAME)
    private String name;

    @OneToMany(mappedBy = ModalityTable.MappedProjects.NAME)
    @JsonBackReference
    private Collection<Project> projects;

    @OneToMany(mappedBy = ModalityTable.MappedPhases.NAME)
    @JsonBackReference
    private Collection<Phase> phases;
}