package backend.siptis.model.entity.semester;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

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

    @Column(name = "start_date")
    private LocalDate startDate;
    @Column(name = "end_date")
    private LocalDate endDate;
    @Column(name = "period")
    private String period;
    @Column(name = "in_progress")
    private boolean inProgress;

    public String getStartDateString() {
        String[] date = startDate.toString().split(" ");
        return date[0];
    }

    public String getEndDateString() {
        String[] date = endDate.toString().split(" ");
        return date[0];
    }
}
