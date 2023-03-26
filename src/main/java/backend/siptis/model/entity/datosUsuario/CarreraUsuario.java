package backend.siptis.model.entity.datosUsuario;

import backend.siptis.auth.entity.Usuario;
import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;

@Entity
@Table(name = "carrera_usuario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CarreraUsuario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;
    private String nombre;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    private Usuario usuario;
}
