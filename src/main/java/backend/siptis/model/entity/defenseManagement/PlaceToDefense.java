package backend.siptis.model.entity.defenseManagement;

import backend.siptis.model.entity.defenseManagement.Defense;
import backend.siptis.utils.constant.entityConstants.DefenseManagementConstants.PlaceToDefenseTable;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;


@Entity
@Table(name = PlaceToDefenseTable.NAME)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlaceToDefense {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = PlaceToDefenseTable.Id.NAME,
            nullable = PlaceToDefenseTable.Id.NULLABLE,
            unique = PlaceToDefenseTable.Id.UNIQUE)
    private Long id;

    @Column(name = PlaceToDefenseTable.Name.NAME)
    private String name;

    @Column(name = PlaceToDefenseTable.Location.NAME)
    private String location;

    @Column(name = PlaceToDefenseTable.Capacity.NAME)
    private Integer capacity;

    @OneToMany(mappedBy = PlaceToDefenseTable.MappedDefenses.NAME)
    @JsonManagedReference
    private Collection<Defense> defenses;
}
