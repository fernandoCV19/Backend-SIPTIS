package backend.siptis.model.pjo.dto.userDataDTO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterAdminDTO {
    @NotEmpty(message = "El correo no puede ser vacio.")
    @Size(min = 2, message = "El correo tiene que ser mayor a 2 caracteres.")
    @Size(max = 50, message = "El correo tiene que ser menor a 30 caracteres.")
    @Pattern(regexp=".+@.+\\..+", message="El correo tiene que cumplir con el formato adecuado.")
    private String email;
    @NotEmpty(message = "La contraseña no puede ser vacio.")
    @Size(min = 6, message = "La contraseña no puede ser menor a 6 caracteres.")
    private String password;
}
