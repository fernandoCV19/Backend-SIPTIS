package backend.siptis.model.pjo.dto.userDataDTO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterAdminDTO {
    @NotEmpty(message = "EMAIL_CANNOT_BE_NULL")
    @Size(min = 2, message = "INVALID_EMAIL_LENGTH")
    @Size(max = 50, message = "INVALID_EMAIL_LENGTH")
    @Pattern(regexp=".+@.+\\..+", message="EMAIL_INVALID_FORMAT")
    private String email;
    @NotEmpty(message = "PASSWORD_CANNOT_BE_NULL")
    @Size(min = 6, message = "INVALID_PASSWORD_LENGTH")
    private String password;
}
