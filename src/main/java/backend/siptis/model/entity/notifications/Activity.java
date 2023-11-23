package backend.siptis.model.entity.notifications;

import backend.siptis.model.entity.project_management.Project;
import backend.siptis.utils.constant.entity_constants.NotificationsConstants.ActivityTable;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = ActivityTable.NAME)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = ActivityTable.Id.NAME,
            nullable = ActivityTable.Id.NULLABLE,
            unique = ActivityTable.Id.UNIQUE)
    private Long id;

    @Column(name = ActivityTable.ActivityName.NAME)
    private String activityName;

    @Column(name = ActivityTable.ActivityDescription.NAME)
    private String activityDescription;

    @Column(name = ActivityTable.ActivityDate.NAME)
    private Date activityDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = ActivityTable.JoinProject.NAME,
            nullable = ActivityTable.JoinProject.NULLABLE)
    @JsonBackReference
    private Project project;
}
