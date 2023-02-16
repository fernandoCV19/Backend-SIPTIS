package BackendSIPTIS.model.entity.datosUsuario;

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
}
