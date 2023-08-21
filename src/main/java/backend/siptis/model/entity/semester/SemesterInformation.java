package backend.siptis.model.entity.semester;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "semester_information")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SemesterInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column (name = "start_date")
    private Date startDate;
    @Column (name = "end_date")
    private Date endDate;
    @Column (name = "in_progress")
    private boolean inProgress;
}
