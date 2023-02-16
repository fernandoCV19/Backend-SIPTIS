package BackendSIPTIS.model.entity.datosUsuario;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "area_usuario")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AreaUsuario {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;
    private String nombre;
}
