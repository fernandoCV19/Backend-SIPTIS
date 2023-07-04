package backend.siptis.model.entity.projectManagement;

import backend.siptis.commons.Phase;
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

    private String phase;

    @OneToOne(mappedBy = "project", cascade = CascadeType.ALL, optional = true)
    @JoinColumn(name = "defense_id")
    private Defense defense;

    @ManyToOne()
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

    @ManyToMany(fetch =  FetchType.LAZY, cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "project_area", joinColumns = @JoinColumn(name = "project_id"), inverseJoinColumns = @JoinColumn(name = "area_id"))
    @JsonManagedReference
    private Collection<Area> areas;

    @OneToMany(mappedBy = "project", fetch = FetchType.LAZY)
    @JsonManagedReference
    private Collection<Presentation> presentations;

    @ManyToOne()
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
}
