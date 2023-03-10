package BackendSIPTIS.auth.entity;

import BackendSIPTIS.model.entity.datosUsuario.AreaUsuario;
import BackendSIPTIS.model.entity.datosUsuario.Documento;
import BackendSIPTIS.model.entity.datosUsuario.Horario;
import BackendSIPTIS.model.entity.editoresYRevisores.*;
import BackendSIPTIS.model.entity.gestionProyecto.Revision;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "usuario")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Usuario implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    private String nombres;
    private String apellidos;
    private String celular;
    private String ci;
    private String email;
    private String contrasena;
    private String codSIS;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(
            name = "usuario_rol",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "rol_id"))
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

    public Usuario(String nombres, String apellidos, String celular,
                   String ci, String email, String contrasena, String codSIS) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.celular = celular;
        this.ci =  ci;
        this.contrasena = contrasena;
        this.codSIS = codSIS;
    }

    //-------------------------------------------------------------------
    public void addRol(Rol rol){
        this.roles.add(rol);
    }

    //-------------------------------------------------------------------
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for(Rol rol: roles){
            authorities.add(new SimpleGrantedAuthority(rol.getNombre()));
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return contrasena;
    }

    @Override
    public String getUsername() {
        return email;
        //return codigoSis;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
