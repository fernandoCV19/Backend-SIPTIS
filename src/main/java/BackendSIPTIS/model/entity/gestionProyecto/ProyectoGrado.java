package BackendSIPTIS.model.entity.gestionProyecto;

import BackendSIPTIS.model.entity.editoresYRevisores.*;
import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;

@Entity
@Table(name = "proyecto_grado")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ProyectoGrado {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    private String nombre;
    private String dirPerfil;
    private String dirLibroAzul;
    private String dirProyecto;
    private String fase;

    @OneToOne
    private Defensa defensa;

    @ManyToOne
    private Modalidad modalidad;

    @ManyToMany
    private Collection<SubArea> subAreas;

    @ManyToMany
    private Collection<AreaProyecto> areas;

    @OneToMany
    private Collection<Presentacion> presentaciones;

    @ManyToOne
    private Estado estado;

    @OneToMany
    private Collection<Actividad> actividades;

    @OneToMany
    private Collection<EstudianteProyecto> estudiantes;

    @OneToMany
    private Collection<SupervisorProyecto> supervisores;

    @OneToMany
    private Collection<TutorProyecto> tutores;

    @OneToMany
    private Collection<DocenteTG2Proyecto> docentes;

    @OneToMany
    private Collection<TribunalProyecto> tribunales;

}
