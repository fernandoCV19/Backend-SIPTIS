package backend.siptis.model.entity.gestionProyecto;

import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;

@Entity
@Table(name = "modalidad")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Modalidad {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    private String nombre;

    @OneToMany(mappedBy = "modalidad")
    private Collection<ProyectoGrado> proyectosDeGrado;
}
