package backend.siptis.model.pjo.dto.general_information.user_area;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateAreaDTO {
    @NotEmpty(message = "NAME_CANNOT_BE_NULL")
    @Size(min = 2, message = "INVALID_NAME_LENGTH")
    private String name;
}
