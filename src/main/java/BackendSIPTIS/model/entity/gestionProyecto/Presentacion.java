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

    @Column(name = "dir_libro_azul")
    private String dirLibroAzul;

    @Column(name = "dir_proyecto")
    private String dirProyecto;
    private String fase;

    @ManyToOne
    @JoinColumn(name = "id_proyecto_grado", nullable = false)
    private ProyectoGrado proyectoGrado;

    @OneToMany(mappedBy = "presentacion")
    private Collection<Revision> revisiones;
}
