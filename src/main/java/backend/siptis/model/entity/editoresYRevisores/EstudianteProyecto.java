package backend.siptis.model.entity.editoresYRevisores;

import backend.siptis.auth.entity.User;
import backend.siptis.model.entity.gestionProyecto.ProyectoGrado;
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
    @JoinColumn(name = "usuario_id", nullable = false)
    private User estudiante;

    @ManyToOne
    @JoinColumn(name = "proyecto_grado_id", nullable = false)
    private ProyectoGrado proyecto;
}
