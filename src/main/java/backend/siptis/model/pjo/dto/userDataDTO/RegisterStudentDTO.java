package backend.siptis.model.pjo.dto.userDataDTO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterStudentDTO extends RegisterUserDTO{
    @NotEmpty(message = "El código SIS no puede ser vacio.")
    @Size(min = 2, message = "El código SIS tiene que ser mayor a 1 digito")
    @Size(max = 50, message = "El código SIS tiene que ser menor a 10 digitos")
    private String codSIS;
    @NotEmpty(message = "La carrera no puede ser vacia.")
    private String career;

}
