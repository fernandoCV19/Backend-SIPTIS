package backend.siptis.model.entity.notifications;

import backend.siptis.utils.constant.entity_constants.NotificationsConstants.GeneralActivityTable;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = GeneralActivityTable.NAME)
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class GeneralActivity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = GeneralActivityTable.Id.NAME,
            nullable = GeneralActivityTable.Id.NULLABLE,
            unique = GeneralActivityTable.Id.UNIQUE)
    private Long id;

    @Column(name = GeneralActivityTable.ActivityName.NAME)
    private String activityName;

    @Column(name = GeneralActivityTable.ActivityDescription.NAME)
    private String activityDescription;

    @Column(name = GeneralActivityTable.ActivityDate.NAME)
    private Date activityDate;
}
