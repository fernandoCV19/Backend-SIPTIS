package backend.siptis.model.entity.projectManagement;

import backend.siptis.model.entity.editorsAndReviewers.*;
import backend.siptis.model.entity.records.Activity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;

@Entity
@Table(name = "project")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    private String name;

    @Column(name = "perfil_path")
    private String perfilPath;

    @Column(name = "blue_book_path")
    private String blueBookPath;

    @Column(name = "project_path")
    private String projectPath;

    private String phase;

    @OneToOne
    private Defense defense;

    @ManyToOne
    @JoinColumn(name = "modality_id", nullable = false)
    @JsonBackReference
    private Modality modality;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "project_sub_area", joinColumns = @JoinColumn(name = "project_id"), inverseJoinColumns = @JoinColumn(name = "sub_area_id"))
    private Collection<SubArea> subAreas;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "project_area", joinColumns = @JoinColumn(name = "project_id"), inverseJoinColumns = @JoinColumn(name = "area_id"))
    private Collection<ProjectArea> areas;

    @OneToMany(mappedBy = "project", fetch = FetchType.LAZY)
    @JsonManagedReference
    private Collection<Presentation> presentations;

    @ManyToOne
    @JoinColumn(name = "state_id", nullable = false)
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

}