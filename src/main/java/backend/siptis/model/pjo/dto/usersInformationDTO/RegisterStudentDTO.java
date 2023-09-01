package backend.siptis.model.pjo.dto.usersInformationDTO;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterStudentDTO extends RegisterUserDTO {
    @NotEmpty(message = "La carrera no puede ser vacia.")
    private String career;
}
