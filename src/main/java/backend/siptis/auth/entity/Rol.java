package backend.siptis.auth.entity;

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
    private Integer id;
    private String nombre;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "roles")
    private Collection<Usuario> usuarios;

    public Rol(String nombre){
        this.nombre = nombre;
    }

    public Rol(Integer id) {
        super();
        this.id = id;
    }

    public String toString(){
        return nombre;
    }
}
