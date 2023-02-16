package BackendSIPTIS.model.entity.editoresYRevisores;

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

    private Long idSupervisor;
    private Long idProyecto;
    private Boolean aceptado;
}
