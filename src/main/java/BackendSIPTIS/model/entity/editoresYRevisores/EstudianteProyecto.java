package BackendSIPTIS.model.entity.editoresYRevisores;

import BackendSIPTIS.auth.entity.Usuario;
import BackendSIPTIS.model.entity.gestionProyecto.ProyectoGrado;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "estudiante_proyecto")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class EstudianteProyecto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @ManyToOne
    private Usuario estudiante;

    @ManyToOne
    private ProyectoGrado proyecto;
}
