package backend.siptis.model.pjo.dto.user_data;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TokenPasswordDTO {
    @NotEmpty(message = "PASSWORD_CANNOT_BE_NULL")
    private String password;
    @NotEmpty(message = "REPEAT_PASSWORD_CANNOT_BE_NULL")
    private String tokenPassword;
}
