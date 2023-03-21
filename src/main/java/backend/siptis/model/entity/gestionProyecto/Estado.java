package backend.siptis.model.entity.gestionProyecto;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;

@Entity
@Table(name = "estado")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Estado {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @OneToMany(mappedBy = "estado", fetch = FetchType.LAZY)
    @JsonManagedReference
    private Collection<ProyectoGrado> proyectosDeGrado;
}
