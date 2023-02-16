package BackendSIPTIS.model.entity.gestionProyecto;

import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;

@Entity
@Table(name = "area_proyecto")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AreaProyecto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    private String nombre;

    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<ProyectoGrado> proyectosDeGrado;
}
