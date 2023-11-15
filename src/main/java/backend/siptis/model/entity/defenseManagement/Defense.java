package backend.siptis.model.entity.defenseManagement;

import backend.siptis.model.entity.projectManagement.Project;
import backend.siptis.utils.constant.entityConstants.DefenseManagementConstants.DefenseTable;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = DefenseTable.NAME)
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Defense {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = DefenseTable.Id.NAME,
            nullable = DefenseTable.Id.NULLABLE,
            unique = DefenseTable.Id.UNIQUE)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = DefenseTable.JoinPlaceToDefense.NAME,
            nullable = DefenseTable.JoinPlaceToDefense.NULLABLE)
    @JsonBackReference
    private PlaceToDefense placeToDefense;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = DefenseTable.JoinProject.NAME,
            nullable = DefenseTable.JoinProject.NULLABLE)
    @JsonBackReference
    private Project project;

    @Column(name = DefenseTable.Date.NAME)
    private LocalDate date;

    @Column(name = DefenseTable.StartTime.NAME)
    private LocalTime startTime;

    @Column(name = DefenseTable.EndTime.NAME)
    private LocalTime endTime;

    @Column(name = DefenseTable.SubstituteName.NAME)
    private String substituteName;

    public Defense(PlaceToDefense placeToDefense, Project project, LocalDate date, LocalTime startTime, LocalTime endTime, String substituteName) {
        this.placeToDefense = placeToDefense;
        this.project = project;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.substituteName = substituteName;
    }
}
