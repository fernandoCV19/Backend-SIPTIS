package BackendSIPTIS.model.entity.editoresYRevisores;

import BackendSIPTIS.auth.entity.Usuario;
import BackendSIPTIS.model.entity.gestionProyecto.ProyectoGrado;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "supervisor_proyecto")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SupervisorProyecto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @ManyToOne
    private Usuario supervisor;

    @ManyToOne
    private ProyectoGrado proyecto;
    private Boolean aceptado;
}
