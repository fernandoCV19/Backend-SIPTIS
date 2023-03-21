package backend.siptis.auth.entity;

import backend.siptis.model.entity.datosUsuario.AreaUsuario;
import backend.siptis.model.entity.datosUsuario.Documento;
import backend.siptis.model.entity.datosUsuario.Horario;
import BackendSIPTIS.model.entity.editoresYRevisores.*;
import backend.siptis.model.entity.editoresYRevisores.*;
import backend.siptis.model.entity.gestionProyecto.Revision;
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

    @OneToMany(mappedBy = "usuario")
    private Collection<Horario> horariosDisponibles;

    @OneToMany(mappedBy = "usuario")
    private Collection<Documento> documentos;

    @OneToMany(mappedBy = "estudiante")
    private Collection<EstudianteProyecto> estudiantes;

    @OneToMany(mappedBy = "supervisor")
    private Collection<SupervisorProyecto> supervisores;

    @OneToMany(mappedBy = "tutor")
    private Collection<TutorProyecto> tutores;

    @OneToMany(mappedBy = "docente")
    private Collection<DocenteTG2Proyecto> docentes;

    @OneToMany(mappedBy = "tribunal")
    private Collection<TribunalProyecto> tribunales;

    @OneToMany(mappedBy = "usuario")
    private Collection<Revision> revisiones;
}
