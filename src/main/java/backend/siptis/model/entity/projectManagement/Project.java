package backend.siptis.model.entity.projectManagement;

import backend.siptis.auth.entity.SiptisUser;
import backend.siptis.model.entity.defenseManagement.Defense;
import backend.siptis.model.entity.editorsAndReviewers.*;
import backend.siptis.model.entity.notifications.Activity;
import backend.siptis.model.entity.presentations.Presentation;
import backend.siptis.model.pjo.dto.userDataDTO.UserListDTO;
import backend.siptis.utils.constant.entityConstants.ProjectManagementConstants.ProjectTable;
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
@Table(name = ProjectTable.NAME)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = ProjectTable.Id.NAME,
            nullable = ProjectTable.Id.NULLABLE,
            unique = ProjectTable.Id.UNIQUE)
    private Long id;

    @Column(name = ProjectTable.Name.NAME)
    private String name;

    @Column(name = ProjectTable.PerfilPath.NAME)
    private String perfilPath;

    @Column(name = ProjectTable.BlueBookPath.NAME)
    private String blueBookPath;

    @Column(name = ProjectTable.ProjectPath.NAME)
    private String projectPath;

    @Column(name = ProjectTable.Phase.NAME)
    private String phase;

    @Column(name = ProjectTable.Period.NAME)
    private String period;

    @OneToOne(mappedBy = ProjectTable.JoinDefense.MAPPED_PROJECT, cascade = CascadeType.ALL)
    @JoinColumn(name = ProjectTable.JoinDefense.NAME)
    @JsonManagedReference
    private Defense defense;

    @ManyToOne
    @JoinColumn(name = ProjectTable.JoinModality.NAME, nullable = false)
    @JsonManagedReference
    private Modality modality;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = ProjectTable.SubAreasRelation.NAME,
            joinColumns = @JoinColumn(name = ProjectTable.SubAreasRelation.JOIN_COLUMN),
            inverseJoinColumns = @JoinColumn(name = ProjectTable.SubAreasRelation.INVERSE_JOIN_COLUMN))
    @JsonManagedReference
    private Collection<SubArea> subAreas;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = ProjectTable.AreasRelation.NAME,
            joinColumns = @JoinColumn(name = ProjectTable.AreasRelation.JOIN_COLUMN),
            inverseJoinColumns = @JoinColumn(name = ProjectTable.AreasRelation.INVERSE_JOIN_COLUMN))
    @JsonManagedReference
    private Set<Area> areas;

    @OneToMany(mappedBy = ProjectTable.MappedPresentations.NAME, fetch = FetchType.LAZY)
    @JsonManagedReference
    private Collection<Presentation> presentations;

    @ManyToOne
    @JoinColumn(name = ProjectTable.JoinState.NAME)
    @JsonBackReference
    private State state;

    @OneToMany(mappedBy = ProjectTable.MappedActivities.NAME, fetch = FetchType.LAZY)
    @JsonManagedReference
    private Collection<Activity> activities;

    @OneToMany(mappedBy = ProjectTable.MappedStudents.NAME, fetch = FetchType.LAZY)
    @JsonManagedReference
    private Collection<ProjectStudent> students;

    @OneToMany(mappedBy = ProjectTable.MappedSupervisors.NAME, fetch = FetchType.LAZY)
    @JsonManagedReference
    private Collection<ProjectSupervisor> supervisors;

    @OneToMany(mappedBy = ProjectTable.MappedTutors.NAME, fetch = FetchType.LAZY)
    @JsonManagedReference
    private Collection<ProjectTutor> tutors;

    @OneToMany(mappedBy = ProjectTable.MappedTeachers.NAME, fetch = FetchType.LAZY)
    @JsonManagedReference
    private Collection<ProjectTeacher> teachers;

    @OneToMany(mappedBy = ProjectTable.MappedTribunals.NAME, fetch = FetchType.LAZY)
    @JsonManagedReference
    private Collection<ProjectTribunal> tribunals;

    @Column(name = ProjectTable.TotalDefensePoints.NAME)
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
