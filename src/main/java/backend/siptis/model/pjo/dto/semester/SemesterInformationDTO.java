package backend.siptis.model.pjo.dto.semester;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class SemesterInformationDTO {
    @NotEmpty(message = "START_DATE_CANNOT_BE_NULL")
    private LocalDate startDate;
    @NotEmpty(message = "END_DATE_CANNOT_BE_NULL")
    private LocalDate endDate;
    @NotEmpty(message = "PERIOD_CANNOT_BE_NULL")
    private String period;
}
