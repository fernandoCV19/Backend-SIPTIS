package backend.siptis.model.pjo.dto.userDataDTO;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class RolesListDTO {
    @NotNull(message = "La lista de roles no puede ser nula.")
    List<Long> roles;
}
