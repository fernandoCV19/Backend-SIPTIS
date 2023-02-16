package BackendSIPTIS.auth.entity;

import BackendSIPTIS.model.entity.datosUsuario.AreaUsuario;
import BackendSIPTIS.model.entity.datosUsuario.Documento;
import BackendSIPTIS.model.entity.datosUsuario.Horario;
import BackendSIPTIS.model.entity.editoresYRevisores.*;
import BackendSIPTIS.model.entity.gestionProyecto.Revision;
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
    private Long id;

    private String nombres;
    private String apellidos;
    private String celular;
    private String ci;
    private String email;
    private String codSIS;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "usuario_rol", joinColumns = @JoinColumn(name = "usuario_id"), inverseJoinColumns = @JoinColumn(name = "rol_id"))
    private Collection<Rol> roles;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "usuario_area", joinColumns = @JoinColumn(name = "usuario_id"), inverseJoinColumns = @JoinColumn(name = "area_id"))
    private Collection<AreaUsuario> areas;

    @OneToMany
    private Collection<Horario> horariosDisponibles;

    @OneToMany(mappedBy = "usuario")
    private Collection<Documento> documentos;

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

    @OneToMany
    private Collection<Revision> revisiones;
}
