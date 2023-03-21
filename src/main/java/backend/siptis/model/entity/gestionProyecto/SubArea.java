package backend.siptis.model.entity.gestionProyecto;

import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;

@Entity
@Table(name = "sub_area")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SubArea {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    private String nombre;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "subAreas")
    private Collection<ProyectoGrado> proyectosDeGrado;
}
