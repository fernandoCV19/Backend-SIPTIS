package backend.siptis.model.pjo.dto.semester;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class SemesterInformationDTO {
    @NotEmpty(message = "Tiene que elegir una fecha de incio.")
    private LocalDate startDate;
    @NotEmpty(message = "Tiene que elegir una fecha de cierre.")
    private LocalDate endDate;
    @NotEmpty(message = "Tiene que elegir un periodo.")
    private String period;
}
