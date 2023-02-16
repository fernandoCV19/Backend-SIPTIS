package BackendSIPTIS.model.entity.gestionProyecto;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "proyecto_grado")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ProyectoGrado {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    private String nombre;
    private String dirPerfil;
    private String dirLibroAzul;
    private String dirProyecto;
    private String fase;
}
