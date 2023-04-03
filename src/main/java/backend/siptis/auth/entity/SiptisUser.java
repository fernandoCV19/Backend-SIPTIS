package backend.siptis.auth.entity;

import backend.siptis.model.entity.userData.*;
import backend.siptis.model.entity.userData.UserArea;
import backend.siptis.model.entity.userData.Document;
import backend.siptis.model.entity.userData.Schedule;
import backend.siptis.model.entity.editorsAndReviewers.*;
import backend.siptis.model.entity.projectManagement.Review;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

import java.util.Collection;

@Entity
@Table(name = "siptis_user")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SiptisUser implements UserDetails {
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
            name = "siptisUser_role",
            joinColumns = @JoinColumn(name = "siptisUser_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER, cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "siptisUser_area",
            joinColumns = @JoinColumn(name = "siptisuser_id"),
            inverseJoinColumns = @JoinColumn(name = "area_id"))
    private Collection<UserArea> areas;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "siptisUser_career",
            joinColumns = @JoinColumn(name = "siptisuser_id"),
            inverseJoinColumns = @JoinColumn(name = "career_id"))
    private Set<UserCareer> career = new HashSet<>();

    /*@OneToOne(mappedBy = "siptisUser")
    private UserCareer career;*/

    @OneToOne(mappedBy = "siptisUser")
    private UserInformation userInformation;

    @OneToMany(mappedBy = "siptisUser")
    private Collection<Schedule> availablesSchedule;

    @OneToMany(mappedBy = "siptisUser")
    private Collection<Document> documents;

    @OneToMany(mappedBy = "student")
    private Collection<ProjectStudent> students;

    @OneToMany(mappedBy = "supervisor")
    private Collection<ProjectSupervisor> supervisors;

    @OneToMany(mappedBy = "tutor")
    private Collection<ProjectTutor> tutors;

    @OneToMany(mappedBy = "teacher")
    private Collection<ProjectTeacher> teachers;

    @OneToMany(mappedBy = "tribunal")
    private Collection<ProjectTribunal> tribunals;

    @OneToMany(mappedBy = "siptisUser")
    private Collection<Review> reviews;

    public SiptisUser(String email, String password) {
        this.email = email;
        this.password = password;
    }

    //-------------------------------------------------------------------
    public void addRol(Role role){
        this.roles.add(role);
    }

    public void addCareer(UserCareer career){
        this.career.add(career);
    }

    //-------------------------------------------------------------------
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        System.out.println("roles:" + roles.toString());
        for(Role role : roles){
            authorities.add(new SimpleGrantedAuthority(role.getName()));
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