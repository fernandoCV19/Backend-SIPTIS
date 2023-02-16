package BackendSIPTIS.model.entity.editoresYRevisores;

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

    private Long idEstudiante;
    private Long idProyecto;
}
