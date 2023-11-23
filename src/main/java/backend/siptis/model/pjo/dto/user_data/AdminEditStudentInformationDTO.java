package backend.siptis.model.pjo.dto.user_data;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdminEditStudentInformationDTO extends AdminEditUserInformationDTO {
    @NotEmpty(message = "CODSIS_CANNOT_BE_NULL")
    @Size(min = 2, message = "INVALID_CODSIS_LENGTH")
    @Size(max = 50, message = "INVALID_CODSIS_LENGTH")
    private String codSIS;
}
