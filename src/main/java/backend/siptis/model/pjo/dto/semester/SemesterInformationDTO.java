package backend.siptis.model.pjo.dto.semester;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class SemesterInformationDTO {
    @NotNull(message = "START_DATE_CANNOT_BE_NULL")
    private LocalDate startDate;
    @NotNull(message = "END_DATE_CANNOT_BE_NULL")
    private LocalDate endDate;
    @NotNull(message = "PERIOD_CANNOT_BE_NULL")
    private String period;
}
