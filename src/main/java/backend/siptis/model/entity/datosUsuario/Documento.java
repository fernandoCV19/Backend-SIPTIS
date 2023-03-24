package backend.siptis.model.entity.datosUsuario;

import backend.siptis.auth.entity.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "documento")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Documento {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;
    private String direccion;
    private String tipo;
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private User user;
}
