package BackendSIPTIS.model.entity.editoresYRevisores;

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

    private Long idDocente;
    private Long idProyecto;
    private Boolean aceptado;
}
