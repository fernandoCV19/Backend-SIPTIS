package backend.siptis.model.entity.projectManagement;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;


@Entity
@Table(name = "place_to_defense")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlaceToDefense {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;
    private String name;
    private String location;
    private Integer capacity;

    @OneToMany(mappedBy = "placeToDefense")
    @JsonManagedReference
    private Collection<Defense> defenses;
}
