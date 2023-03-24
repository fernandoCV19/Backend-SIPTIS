package backend.siptis.model.entity.datosUsuario;

import backend.siptis.auth.entity.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "horario")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Horario {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;
    private String dia;
    private String hora;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private User user;
}
