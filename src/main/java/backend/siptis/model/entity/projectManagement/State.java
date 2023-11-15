package backend.siptis.model.entity.projectManagement;

import backend.siptis.utils.constant.entityConstants.ProjectManagementConstants.StateTable;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;

@Entity
@Table(name = StateTable.NAME)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class State {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = StateTable.Id.NAME,
            nullable = StateTable.Id.NULLABLE,
            unique = StateTable.Id.UNIQUE)
    private Long id;

    @Column(name = StateTable.Name.NAME)
    private String name;

    @OneToMany(mappedBy = StateTable.MappedProjects.NAME)
    @JsonManagedReference
    private Collection<Project> projects;
}
