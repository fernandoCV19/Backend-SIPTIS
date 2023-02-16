package BackendSIPTIS.model.entity.editoresYRevisores;

import BackendSIPTIS.auth.entity.Usuario;
import BackendSIPTIS.model.entity.gestionProyecto.ProyectoGrado;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tutor_proyecto")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TutorProyecto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @ManyToOne
    private Usuario tutor;

    @ManyToOne
    private ProyectoGrado proyectoGrado;
    private Boolean aceptado;
}
