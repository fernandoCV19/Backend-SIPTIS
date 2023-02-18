package BackendSIPTIS.model.entity.gestionProyecto;

import BackendSIPTIS.commons.FaseProyecto;
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

    @Column(name = "dir_perfil")
    private String dirPerfil;

    @Column(name = "dir_libro_azul")
    private String dirLibroAzul;

    @Column(name = "dir_proyecto")
    private String dirProyecto;
    private FaseProyecto fase;

    @OneToOne
    private Defensa defensa;

    @ManyToOne
    @JoinColumn(name = "modalidad_id", nullable = false)
    private Modalidad modalidad;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "proyecto_grado_sub_area", joinColumns = @JoinColumn(name = "proyecto_grado_id"), inverseJoinColumns = @JoinColumn(name = "sub_area_id"))
    private Collection<SubArea> subAreas;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "proyecto_grado_area", joinColumns = @JoinColumn(name = "proyecto_grado_id"), inverseJoinColumns = @JoinColumn(name = "area_id"))
    private Collection<AreaProyecto> areas;

    @OneToMany(mappedBy = "proyectoGrado")
    private Collection<Presentacion> presentaciones;

    @ManyToOne
    @JoinColumn(name = "estado_id", nullable = false)
    private Estado estado;

    @OneToMany(mappedBy = "proyectoGrado")
    private Collection<Actividad> actividades;

    @OneToMany(mappedBy = "proyecto")
    private Collection<EstudianteProyecto> estudiantes;

    @OneToMany(mappedBy = "proyecto")
    private Collection<SupervisorProyecto> supervisores;

    @OneToMany(mappedBy = "proyecto")
    private Collection<TutorProyecto> tutores;

    @OneToMany(mappedBy = "proyecto")
    private Collection<DocenteTG2Proyecto> docentes;

    @OneToMany(mappedBy = "proyecto")
    private Collection<TribunalProyecto> tribunales;

}
