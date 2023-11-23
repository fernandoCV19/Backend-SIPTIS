package backend.siptis.model.pjo.dto.user_data;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RolesListDTO {
    @NotNull(message = "ROLES_CANNOT_BE_NULL")
    List<Long> roles;
}
