package backend.siptis.model.pjo.dto.userDataDTO;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class RolesListDTO {
    @NotNull(message = "La lista de roles no puede ser nula.")
    ArrayList<Long> roles;
}
