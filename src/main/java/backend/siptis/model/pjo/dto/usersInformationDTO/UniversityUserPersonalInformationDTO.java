package backend.siptis.model.pjo.dto.usersInformationDTO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class UniversityUserPersonalInformationDTO extends GeneralUserPersonalInformationDTO {
    @NotEmpty(message = "El código SIS no puede ser vacio.")
    @Size(min = 2, message = "El código SIS tiene que ser mayor a 1 digito")
    @Size(max = 50, message = "El código SIS tiene que ser menor a 10 digitos")
    private String codSIS;

}
