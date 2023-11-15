package backend.siptis.auth.entity;

import backend.siptis.model.entity.editorsAndReviewers.*;
import backend.siptis.model.entity.presentations.Review;
import backend.siptis.model.entity.userData.*;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Entity
@Table(name = "siptis_user")
@Getter
@Setter
@NoArgsConstructor
public class SiptisUser implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;
    private String email;
    private String password;
    private String tokenPassword;


    @ManyToMany(fetch = FetchType.LAZY, cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(
            name = "siptisUser_role",
            joinColumns = @JoinColumn(name = "siptisUser_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    @JsonManagedReference
    private Set<Role> roles = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "siptisUser_area",
            joinColumns = @JoinColumn(name = "siptisuser_id"),
            inverseJoinColumns = @JoinColumn(name = "area_id"))
    @JsonManagedReference
    private Set<UserArea> areas;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "siptisUser_career",
            joinColumns = @JoinColumn(name = "siptisuser_id"),
            inverseJoinColumns = @JoinColumn(name = "career_id"))
    @JsonManagedReference
    private Set<UserCareer> career = new HashSet<>();


    @OneToOne(mappedBy = "siptisUser", cascade = CascadeType.ALL, optional = true)
    private UserInformation userInformation;

    @OneToOne(mappedBy = "siptisUser", cascade = CascadeType.ALL, optional = true)
    private RefreshToken refreshToken;


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "siptisUser")
    @JsonManagedReference
    private Collection<Schedule> availableSchedules;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "siptisUser")
    @JsonManagedReference
    private Collection<Document> documents;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "student")
    @JsonManagedReference
    private Collection<ProjectStudent> students;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "supervisor")
    @JsonManagedReference
    private Collection<ProjectSupervisor> supervisorOf;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "tutor")
    @JsonManagedReference
    private Collection<ProjectTutor> tutorOf;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "teacher")
    @JsonManagedReference
    private Collection<ProjectTeacher> teacherOf;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "tribunal")
    @JsonManagedReference
    private Collection<ProjectTribunal> tribunalOf;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "siptisUser")
    @JsonManagedReference
    private Collection<Review> reviews;

    public SiptisUser(String email, String password) {
        this.email = email;
        this.password = password;
        this.tokenPassword = null;
    }

    //-------------------------------------------------------------------
    public void addRol(Role role) {
        this.roles.add(role);
    }

    public void addCareer(UserCareer career) {
        this.career.add(career);
    }

    public void addAreas(UserArea area) {
        this.areas.add(area);
    }

    //-------------------------------------------------------------------
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public ArrayList<Long> getProjects() {
        Collection<ProjectStudent> projects = getStudents();
        ArrayList<Long> ids = new ArrayList<>();
        if (projects == null)
            return ids;
        for (ProjectStudent project : projects) {
            ids.add(project.getProject().getId());
        }
        return ids;
    }

    public String getFullName() {
        return userInformation.getNames() + " " + userInformation.getLastNames();
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
