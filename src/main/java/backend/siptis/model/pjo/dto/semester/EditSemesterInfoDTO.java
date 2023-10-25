package backend.siptis.model.pjo.dto.semester;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class EditSemesterInfoDTO {
    @NotNull(message = "Tiene que ingresar el id del semestre en curso.")
    private Long id;
    @NotNull(message = "Tiene que elegir una fecha de incio.")
    private LocalDate startDate;
    @NotNull(message = "Tiene que elegir una fecha de cierre.")
    private LocalDate endDate;
    @NotNull(message = "Tiene que elegir un periodo.")
    private String period;
}
