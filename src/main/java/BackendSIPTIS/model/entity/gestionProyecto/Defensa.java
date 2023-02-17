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

    @Column(name = "lugar_defensa")
    private String lugarDefensa;

    @Column(name = "hora_defensa")
    private String horaDefensa;

    @OneToOne
    private ProyectoGrado proyectoGrado;
}
