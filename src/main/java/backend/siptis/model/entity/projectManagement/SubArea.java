package backend.siptis.model.entity.projectManagement;

import backend.siptis.utils.constant.entityConstants.ProjectManagementConstants.SubAreaTable;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;

@Entity
@Table(name = SubAreaTable.NAME)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SubArea {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = SubAreaTable.Id.NAME,
            nullable = SubAreaTable.Id.NULLABLE,
            unique = SubAreaTable.Id.UNIQUE)
    private Long id;

    @Column(name = SubAreaTable.Name.NAME)
    private String name;

    @ManyToMany(mappedBy = SubAreaTable.MappedProjects.NAME)
    @JsonBackReference
    private Collection<Project> projects;
}
