package BackendSIPTIS.auth.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;

@Entity
@Table(name = "rol")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;
    private String nombre;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "roles")
    private Collection<Usuario> usuarios;
}
