package backend.siptis.model.pjo.dto.user_data;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class UserEditInformationDTO {
    @NotEmpty(message = "EMAIL_CANNOT_BE_NULL")
    @Size(min = 2, message = "INVALID_EMAIL_LENGTH")
    @Size(max = 50, message = "INVALID_EMAIL_LENGTH")
    @Pattern(regexp = ".+@.+\\..+", message = "EMAIL_INVALID_FORMAT")
    private String email;
    @NotEmpty(message = "CEL_NUMBER_CANNOT_BE_NULL")
    @Size(min = 2, message = "INVALID_CEL_NUMBER_LENGTH")
    @Size(max = 50, message = "INVALID_CEL_NUMBER_LENGTH")
    private String celNumber;
    @NotNull(message = "BIRTH_DATE_CANNOT_BE_NULL")
    private Date birthDate;
}

