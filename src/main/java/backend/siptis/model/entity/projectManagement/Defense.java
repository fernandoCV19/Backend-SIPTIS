package backend.siptis.model.entity.projectManagement;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "defense")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Defense {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, unique = true)
    private Integer id;

    private String place;

    private String defenseHour;
}
