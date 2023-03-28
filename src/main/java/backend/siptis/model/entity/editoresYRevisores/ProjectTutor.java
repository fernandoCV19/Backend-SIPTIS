package backend.siptis.model.entity.editoresYRevisores;

import backend.siptis.auth.entity.Usuario;
import backend.siptis.model.entity.gestionProyecto.ProyectoGrado;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tutor_proyecto")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ProjectTutor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario tutor;

    @ManyToOne
    @JoinColumn(name = "proyecto_grado_id", nullable = false)
    private ProyectoGrado project;
    private Boolean accepted;
    private Boolean reviewed;
}
