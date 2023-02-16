package BackendSIPTIS.model.entity.gestionProyecto;

import BackendSIPTIS.auth.entity.Usuario;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "revision")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Revision {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "dir_documento")
    private String dirDocumento;
    private String comentario;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "presentacion_id", nullable = false)
    private Presentacion presentacion;
}
