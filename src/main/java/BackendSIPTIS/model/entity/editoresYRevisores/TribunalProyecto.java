package BackendSIPTIS.model.entity.editoresYRevisores;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tribunal_proyecto")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TribunalProyecto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    private Long idTribunal;
    private Long idProyecto;
    private Boolean aceptado;

    @Column(name = "nota_defensa")
    private Double notaDefensa;
}
