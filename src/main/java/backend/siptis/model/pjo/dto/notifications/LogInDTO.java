package backend.siptis.model.pjo.dto.notifications;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LogInDTO {
    @NotEmpty(message = "EMAIL_CANNOT_BE_NULL")
    private String email;
    @NotEmpty(message = "PASSWORD_CANNOT_BE_NULL")
    private String password;
}

