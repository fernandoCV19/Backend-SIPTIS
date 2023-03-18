package backend.siptis.model.entity.datosUsuario;

import backend.siptis.auth.entity.Usuario;
import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;

@Entity
@Table(name = "area_usuario")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class
AreaUsuario {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;
    private String nombre;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "areas")
    private Collection<Usuario> usuarios;
}
