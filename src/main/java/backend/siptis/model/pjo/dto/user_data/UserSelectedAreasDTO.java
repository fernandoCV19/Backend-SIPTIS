package backend.siptis.model.pjo.dto.user_data;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserSelectedAreasDTO {
    @NotNull(message = "AREAS_CANNOT_BE_NULL")
    private List<Long> ids;
}
