package backend.siptis.model.entity.datosUsuario;

import backend.siptis.auth.entity.Usuario;
import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;

@Entity
@Table(name = "carrera_usuario")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CarreraUsuario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;
    private String nombre;

    @OneToOne(fetch = FetchType.EAGER, mappedBy = "carrera")
    private Usuario usuario;
}
