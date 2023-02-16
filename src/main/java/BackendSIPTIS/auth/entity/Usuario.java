package BackendSIPTIS.auth.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;

@Entity
@Table(name = "usuario")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, unique = true)
    private Integer id;

    private String nombres;
    private String apellidos;
    private String celular;
    private String ci;
    private String email;
    private String codSIS;
    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Rol> roles;
}
