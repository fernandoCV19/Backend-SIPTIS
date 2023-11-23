package backend.siptis.model.entity.project_management;

import backend.siptis.utils.constant.entity_constants.ProjectManagementConstants.AreaTable;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;

@Entity
@Table(name = AreaTable.NAME)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Area {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = AreaTable.Id.NAME,
            nullable = AreaTable.Id.NULLABLE,
            unique = AreaTable.Id.UNIQUE)
    private Long id;

    @Column(name = AreaTable.Name.NAME)
    private String name;

    @ManyToMany(mappedBy = AreaTable.MappedProjects.NAME)
    @JsonBackReference
    private Collection<Project> projects;
}
