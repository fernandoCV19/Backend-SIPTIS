package backend.siptis.auth.entity;

import backend.siptis.model.entity.editors_and_reviewers.*;
import backend.siptis.model.entity.presentations.Review;
import backend.siptis.model.entity.user_data.*;
import backend.siptis.utils.constant.entity_constants.AuthConstants.SiptisUserTable;
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
@Table(name = SiptisUserTable.NAME)
@Getter
@Setter
@NoArgsConstructor
public class SiptisUser implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = SiptisUserTable.Id.NAME,
            nullable = SiptisUserTable.Id.NULLABLE,
            unique = SiptisUserTable.Id.UNIQUE)
    private Long id;

    @Column(name = SiptisUserTable.Email.NAME)
    private String email;

    @Column(name = SiptisUserTable.Password.NAME)
    private String password;

    @Column(name = SiptisUserTable.TokenPassword.NAME)
    private String tokenPassword;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(
            name = SiptisUserTable.RolesRelation.NAME,
            joinColumns = @JoinColumn(name = SiptisUserTable.RolesRelation.JOIN_COLUMN),
            inverseJoinColumns = @JoinColumn(name = SiptisUserTable.RolesRelation.INVERSE_JOIN_COLUMN))
    @JsonManagedReference
    private Set<Role>
            roles = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = SiptisUserTable.AreasRelation.NAME,
            joinColumns = @JoinColumn(name = SiptisUserTable.AreasRelation.JOIN_COLUMN),
            inverseJoinColumns = @JoinColumn(name = SiptisUserTable.AreasRelation.INVERSE_JOIN_COLUMN))
    @JsonManagedReference
    private Set<UserArea> areas;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = SiptisUserTable.CareersRelation.NAME,
            joinColumns = @JoinColumn(name = SiptisUserTable.CareersRelation.JOIN_COLUMN),
            inverseJoinColumns = @JoinColumn(name = SiptisUserTable.CareersRelation.INVERSE_JOIN_COLUMN))
    @JsonManagedReference
    private Set<UserCareer> career = new HashSet<>();


    @OneToOne(mappedBy = SiptisUserTable.MappedUserInformation.NAME, cascade = CascadeType.ALL)
    private UserInformation userInformation;

    @OneToOne(mappedBy = SiptisUserTable.MappedRefreshToken.NAME, cascade = CascadeType.ALL)
    private RefreshToken refreshToken;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = SiptisUserTable.MappedAvailableSchedules.NAME)
    @JsonManagedReference
    private Collection<Schedule> availableSchedules;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = SiptisUserTable.MappedDocuments.NAME)
    @JsonManagedReference
    private Collection<Document> documents;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = SiptisUserTable.MappedStudents.NAME)
    @JsonManagedReference
    private Collection<ProjectStudent> students;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = SiptisUserTable.MappedSupervisorOf.NAME)
    @JsonManagedReference
    private Collection<ProjectSupervisor> supervisorOf;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = SiptisUserTable.MappedTutorOf.NAME)
    @JsonManagedReference
    private Collection<ProjectTutor> tutorOf;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = SiptisUserTable.MappedTeacherOf.NAME)
    @JsonManagedReference
    private Collection<ProjectTeacher> teacherOf;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = SiptisUserTable.MappedTribunalOf.NAME)
    @JsonManagedReference
    private Collection<ProjectTribunal> tribunalOf;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = SiptisUserTable.MappedReviews.NAME)
    @JsonManagedReference
    private Collection<Review> reviews;

    public SiptisUser(String email, String password) {
        this.email = email;
        this.password = password;
        this.tokenPassword = null;
    }

    public void addRol(Role role) {
        this.roles.add(role);
    }

    public void addCareer(UserCareer career) {
        this.career.add(career);
    }

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

    public List<Long> getProjects() {
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
