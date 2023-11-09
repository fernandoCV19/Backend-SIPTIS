package backend.siptis.model.entity.projectManagement;

import backend.siptis.auth.entity.SiptisUser;
import backend.siptis.model.entity.editorsAndReviewers.*;
import backend.siptis.model.entity.notifications.Activity;
import backend.siptis.model.pjo.dto.userDataDTO.UserListDTO;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "project")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "report_index", columnDefinition = "integer default 0")
    private Integer reportIndex = 0;

    private String name;

    @Column(name = "perfil_path")
    private String perfilPath;

    @Column(name = "blue_book_path")
    private String blueBookPath;

    @Column(name = "project_path")
    private String projectPath;

    @Column(name = "period")
    private String period;

    private String phase;

    @OneToOne(mappedBy = "project", cascade = CascadeType.ALL, optional = true)
    @JoinColumn(name = "defense_id")
    @JsonManagedReference
    private Defense defense;

    @ManyToOne
    @JoinColumn(name = "modality_id", nullable = false)
    @JsonManagedReference
    private Modality modality;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "project_sub_area", joinColumns = @JoinColumn(name = "project_id"), inverseJoinColumns = @JoinColumn(name = "sub_area_id"))
    @JsonManagedReference
    private Collection<SubArea> subAreas;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "project_area", joinColumns = @JoinColumn(name = "project_id"), inverseJoinColumns = @JoinColumn(name = "area_id"))
    @JsonManagedReference
    private Set<Area> areas;

    @OneToMany(mappedBy = "project", fetch = FetchType.LAZY)
    @JsonManagedReference
    private Collection<Presentation> presentations;

    @ManyToOne
    @JoinColumn(name = "state_id", nullable = true)
    @JsonBackReference
    private State state;

    @OneToMany(mappedBy = "project", fetch = FetchType.LAZY)
    @JsonManagedReference
    private Collection<Activity> activities;

    @OneToMany(mappedBy = "project", fetch = FetchType.LAZY)
    @JsonManagedReference
    private Collection<ProjectStudent> students;

    @OneToMany(mappedBy = "project", fetch = FetchType.LAZY)
    @JsonManagedReference
    private Collection<ProjectSupervisor> supervisors;

    @OneToMany(mappedBy = "project", fetch = FetchType.LAZY)
    @JsonManagedReference
    private Collection<ProjectTutor> tutors;

    @OneToMany(mappedBy = "project", fetch = FetchType.LAZY)
    @JsonManagedReference
    private Collection<ProjectTeacher> teachers;

    @OneToMany(mappedBy = "project", fetch = FetchType.LAZY)
    @JsonManagedReference
    private Collection<ProjectTribunal> tribunals;

    @Column(name = "total_defense_points")
    private Double totalDefensePoints;


    public List<UserListDTO> getProjectStudents() {
        ArrayList<UserListDTO> studentsNames = new ArrayList<>();
        SiptisUser user;
        UserListDTO userListDTO;
        for (ProjectStudent student : students) {
            user = student.getStudent();
            userListDTO = new UserListDTO();
            userListDTO.setId(user.getId());
            userListDTO.setName(user.getFullName());
            studentsNames.add(userListDTO);
        }
        return studentsNames;
    }

    public List<UserListDTO> getProjectTutors() {
        ArrayList<UserListDTO> tutorsNames = new ArrayList<>();
        SiptisUser user;
        UserListDTO userListDTO;
        for (ProjectTutor tutor : tutors) {
            user = tutor.getTutor();
            userListDTO = new UserListDTO();
            userListDTO.setId(user.getId());
            userListDTO.setName(user.getFullName());
            tutorsNames.add(userListDTO);
        }
        return tutorsNames;
    }

    public List<UserListDTO> getProjectTeachers() {
        ArrayList<UserListDTO> teachersNames = new ArrayList<>();
        SiptisUser user;
        UserListDTO userListDTO;
        for (ProjectTeacher teacher : teachers) {
            user = teacher.getTeacher();
            userListDTO = new UserListDTO();
            userListDTO.setId(user.getId());
            userListDTO.setName(user.getFullName());
            teachersNames.add(userListDTO);
        }
        return teachersNames;
    }

    public List<UserListDTO> getProjectSupervisors() {
        ArrayList<UserListDTO> supervisorsNames = new ArrayList<>();
        SiptisUser user;
        UserListDTO userListDTO;
        for (ProjectSupervisor supervisor : supervisors) {
            user = supervisor.getSupervisor();
            userListDTO = new UserListDTO();
            userListDTO.setId(user.getId());
            userListDTO.setName(user.getFullName());
            supervisorsNames.add(userListDTO);
        }
        return supervisorsNames;
    }

    public List<UserListDTO> getProjectTribunals() {
        ArrayList<UserListDTO> tutorsNames = new ArrayList<>();
        SiptisUser user;
        UserListDTO userListDTO;
        for (ProjectTribunal tribunal : tribunals) {
            user = tribunal.getTribunal();
            userListDTO = new UserListDTO();
            userListDTO.setId(user.getId());
            userListDTO.setName(user.getFullName());
            tutorsNames.add(userListDTO);
        }
        return tutorsNames;
    }

    public List<String> getAreasNames() {
        ArrayList<String> areasNames = new ArrayList<>();
        for (Area area : areas) {
            areasNames.add(area.getName());
        }
        return areasNames;
    }

    public List<String> getSubAreasNames() {
        ArrayList<String> subAreasNames = new ArrayList<>();
        for (SubArea subArea : subAreas) {
            subAreasNames.add(subArea.getName());
        }
        return subAreasNames;
    }

}
