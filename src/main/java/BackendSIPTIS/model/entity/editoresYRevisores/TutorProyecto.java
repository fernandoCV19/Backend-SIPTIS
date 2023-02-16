package BackendSIPTIS.model.entity.editoresYRevisores;

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

    private Long idTutor;
    private Long idProyecto;
    private Boolean aceptado;
}
