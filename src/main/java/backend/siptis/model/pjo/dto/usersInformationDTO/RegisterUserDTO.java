package backend.siptis.model.pjo.dto.usersInformationDTO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class RegisterUserDTO {
    @NotEmpty(message = "Los nombres no pueden ser vacios.")
    @Size(min = 2, message = "Los nombres tienen que ser mayor a 1 caracter")
    @Size(max = 50, message = "Los nombres tienen que ser menor a 50 caracteres")
    private String names;
    @NotEmpty(message = "Los apellidos no pueden ser vacios.")
    @Size(min = 2, message = "Los apellidos tienen que ser mayor a 1 caracter")
    @Size(max = 50, message = "Los apellidos tienen que ser menor a 50 caracteres")
    private String lastnames;
    @NotEmpty(message = "El correo no puede ser vacio.")
    @Size(min = 2, message = "El correo tiene que ser mayor a 1 caracter.")
    @Pattern(regexp = "^(.+)@(\\S+)$", message = "El correo tiene que cumplir con el formato adecuado.")
    @Size(max = 50, message = "El correo tiene que ser menor a 50 caracteres.")
    private String email;
    @NotEmpty(message = "La contraseña no pueden ser vacia.")
    @Size(min = 2, message = "La contraseña tiene que ser mayor a 6 caracteres")
    @Size(max = 50, message = "La contraseña tiene que ser menor a 20 caracteres")
    private String password;
    @NotEmpty(message = "El número personal no puede ser vacio.")
    @Size(min = 2, message = "El número personal tiene que ser mayor a 1 digito")
    @Size(max = 50, message = "El número tiene que ser menor a 10 digitos")
    private String celNumber;
    @NotEmpty(message = "El documento de identidad no puede ser vacio.")
    @Size(min = 2, message = "El documento de identidad tiene que ser mayor a 1 digito")
    @Size(max = 50, message = "El documento de identidad tiene que ser menor a 10 digitos")
    private String ci;

    private Date birthDate;

    @NotEmpty(message = "El código SIS no puede ser vacio.")
    @Size(min = 2, message = "El código SIS tiene que ser mayor a 1 digito")
    @Size(max = 50, message = "El código SIS tiene que ser menor a 10 digitos")
    private String codSIS;
}
