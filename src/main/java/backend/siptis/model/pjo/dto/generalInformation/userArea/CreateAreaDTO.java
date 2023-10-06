package backend.siptis.model.pjo.dto.generalInformation.userArea;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateAreaDTO {

    @NotEmpty(message = "Los nombres no pueden ser vacios.")
    @Size(min = 2, message = "El nombre del area tiene que ser mayor a 1 caracter")
    private String name;
}
