package backend.siptis.model.pjo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class UserEditPersonalInformationDTO {

    @NotEmpty(message = "El correo no puede ser vacio.")
    @Size(min = 2, message = "El correo tiene que ser mayor a 1 caracter.")
    @Pattern(regexp = "^(.+)@(\\S+)$", message = "El correo tiene que cumplir con el formato adecuado.")
    @Size(max = 50, message = "El correo tiene que ser menor a 50 caracteres.")
    private String email;

    @NotEmpty(message = "El número personal no puede ser vacio.")
    @Size(min = 2, message = "El número personal tiene que ser mayor a 1 digito")
    @Size(max = 50, message = "El número tiene que ser menor a 10 digitos")
    private String celNumber;

    private Date birthDate;
}
