package backend.siptis.model.entity.semester;

import backend.siptis.utils.constant.entityConstants.SemesterConstants.SemesterInformationTable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = SemesterInformationTable.NAME)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SemesterInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = SemesterInformationTable.Id.NAME,
            nullable = SemesterInformationTable.Id.NULLABLE,
            unique = SemesterInformationTable.Id.UNIQUE)
    private Long id;

    @Column(name = SemesterInformationTable.StartDate.NAME)
    private LocalDate startDate;

    @Column(name = SemesterInformationTable.EndDate.NAME)
    private LocalDate endDate;

    @Column(name = SemesterInformationTable.Period.NAME)
    private String period;

    @Column(name = SemesterInformationTable.InProgress.NAME)
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
