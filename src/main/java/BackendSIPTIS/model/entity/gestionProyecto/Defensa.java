package BackendSIPTIS.model.entity.gestionProyecto;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "defensa")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Defensa {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    private String lugarDefensa;
    private String horaDefensa;

    @OneToOne
    private ProyectoGrado proyectoGrado;
}
