package BackendSIPTIS.model.entity.editoresYRevisores;

import BackendSIPTIS.auth.entity.Usuario;
import BackendSIPTIS.model.entity.gestionProyecto.ProyectoGrado;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tribunal_proyecto")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TribunalProyecto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @ManyToOne
    private ProyectoGrado proyectoGrado;

    @ManyToOne
    private Usuario tribunal;

    private Boolean aceptado;

    @Column(name = "nota_defensa")
    private Double notaDefensa;
}
