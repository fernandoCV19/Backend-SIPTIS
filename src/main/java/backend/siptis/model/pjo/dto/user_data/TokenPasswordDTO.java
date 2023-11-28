package backend.siptis.model.pjo.dto.user_data;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TokenPasswordDTO {
    @NotEmpty(message = "PASSWORD_CANNOT_BE_NULL")
    @Size(min = 8, message = "INVALID_PASSWORD_LENGTH")
    @Pattern(regexp = ".*[0-9].*", message = "INVALID_PASSWORD_FORMAT")
    @Pattern(regexp = ".*[A-Z].*", message = "INVALID_PASSWORD_FORMAT")
    private String password;
    @NotEmpty(message = "REPEAT_PASSWORD_CANNOT_BE_NULL")
    private String tokenPassword;
}
