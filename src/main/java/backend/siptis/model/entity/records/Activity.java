package backend.siptis.model.entity.records;

import backend.siptis.model.entity.projectManagement.Project;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "activity")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "activity_name")
    private String activityName;
    @Column(name = "activity_description")
    private String activityDescription;
    @Column(name = "activity_date")
    private Date activityDate;

    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;
}
