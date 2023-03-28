package backend.siptis.model.entity.gestionProyecto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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

    @Column(name = "fase")
    private String fase;

    @Column(name = "revisado")
    private Boolean revisado = false;

    @ManyToOne
    @JoinColumn(name = "proyecto_grado_id", nullable = false)
    @JsonBackReference
    private ProyectoGrado proyectoGrado;

    @OneToMany(mappedBy = "presentacion",  fetch = FetchType.LAZY)
    @JsonManagedReference
    private Collection<Revision> revisiones;
}
