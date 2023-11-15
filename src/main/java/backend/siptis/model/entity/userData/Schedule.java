package backend.siptis.model.entity.userData;

import backend.siptis.auth.entity.SiptisUser;
import backend.siptis.utils.constant.entityConstants.UserDataConstants.ScheduleTable;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = ScheduleTable.NAME)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = ScheduleTable.Id.NAME,
            nullable = ScheduleTable.Id.NULLABLE,
            unique = ScheduleTable.Id.UNIQUE)
    private Long id;

    @Column(name = ScheduleTable.Day.NAME)
    private String day;

    @Column(name = ScheduleTable.HourStart.NAME)
    private String hourStart;

    @Column(name = ScheduleTable.HourFinish.NAME)
    private String hourFinish;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    @JoinColumn(name = ScheduleTable.JoinSiptisUser.NAME, nullable = false)
    private SiptisUser siptisUser;
}
