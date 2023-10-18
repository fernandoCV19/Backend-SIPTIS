package backend.siptis.model.pjo.dto.userDataDTO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
public class UserEditInformationDTO {
    @NotEmpty(message = "El correo no puede ser vacio.")
    @Size(min = 2, message = "El correo tiene que ser mayor a 2 caracteres.")
    @Size(max = 50, message = "El correo tiene que ser menor a 30 caracteres.")
    @Pattern(regexp=".+@.+\\..+", message="El correo tiene que cumplir con el formato adecuado.")
    private String email;
    @NotEmpty(message = "El número personal no puede ser vacio.")
    @Size(min = 2, message = "El número personal tiene que ser mayor a 5 digitos")
    @Size(max = 50, message = "El número tiene que ser menor a 10 digitos")
    private String celNumber;
    @NotNull(message = "La fecha de nacimiento no puede ser vacia.")
    private Date birthDate;
}

