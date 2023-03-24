package backend.siptis.auth.entity;

import backend.siptis.model.entity.datosUsuario.*;
import backend.siptis.model.entity.datosUsuario.UserArea;
import backend.siptis.model.entity.datosUsuario.Documento;
import backend.siptis.model.entity.datosUsuario.Horario;
import backend.siptis.model.entity.editoresYRevisores.*;
import backend.siptis.model.entity.gestionProyecto.Revision;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

import java.util.Collection;

@Entity
@Table(name = "user")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;
    private String email;
    private String password;


    @ManyToMany(fetch = FetchType.EAGER, cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER, cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "user_area",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "area_id"))
    private Collection<UserArea> areas;

    @OneToOne(mappedBy = "user")
    private UserCareer career;

    @OneToOne(mappedBy = "user")
    private UserInformation userInformation;

    @OneToMany(mappedBy = "user")
    private Collection<Horario> horariosDisponibles;

    @OneToMany(mappedBy = "user")
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

    @OneToMany(mappedBy = "user")
    private Collection<Revision> revisiones;

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    //-------------------------------------------------------------------
    public void addRol(Role role){
        this.roles.add(role);
    }

    //-------------------------------------------------------------------
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        System.out.println("roles:" + roles.toString());
        for(Role role : roles){
            authorities.add(new SimpleGrantedAuthority(role.getRolName()));
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
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
