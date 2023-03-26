package backend.siptis.model.entity.datosUsuario;

import backend.siptis.auth.entity.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "informacion_usuario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InformacionUsuario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    private String nombres;
    private String apellidos;
    private String celular;
    private String ci;
    private Date fechaNac;
    private String codSIS;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    private Usuario usuario;
}
