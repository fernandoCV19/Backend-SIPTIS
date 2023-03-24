package backend.siptis.model.entity.datosUsuario;

import backend.siptis.auth.entity.User;
import jakarta.persistence.*;
import lombok.*;

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
    private User user;
}
