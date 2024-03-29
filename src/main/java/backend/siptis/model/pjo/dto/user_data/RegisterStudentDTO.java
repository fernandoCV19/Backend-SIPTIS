package backend.siptis.model.pjo.dto.user_data;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterStudentDTO extends RegisterUserDTO {
    @NotEmpty(message = "CODSIS_CANNOT_BE_NULL")
    @Size(min = 8, message = "INVALID_CODSIS_LENGTH")
    @Size(max = 10, message = "INVALID_CODSIS_LENGTH")
    private String codSIS;
    @NotEmpty(message = "CAREER_CANNOT_BE_NULL")
    private String career;

}
