package BackendSIPTIS.model.entity.editoresYRevisores;

import BackendSIPTIS.auth.entity.Usuario;
import BackendSIPTIS.model.entity.gestionProyecto.ProyectoGrado;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "docente_tg2_proyecto")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class DocenteTG2Proyecto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @ManyToOne
    private Usuario docente;

    @ManyToOne
    private ProyectoGrado proyectoGrado;
    private Boolean aceptado;
}
