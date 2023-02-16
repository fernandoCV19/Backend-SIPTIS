package BackendSIPTIS.model.entity.gestionProyecto;

import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;

@Entity
@Table(name = "presentacion")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Presentacion {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    private String dirLibroAzul;
    private String dirProyecto;
    private String fase;

    @ManyToOne
    private ProyectoGrado proyectoGrado;

    @OneToMany
    private Collection<Revision> revisiones;
}
