package backend.siptis.model.entity.notifications;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "general_activity")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class GeneralActivity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column (name = "activity_name")
    private String activityName;
    @Column(name = "activity_description")
    private String activityDescription;
    @Column(name = "activity_date")
    private Date activityDate;
}
