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
public class AdminEditUserInformationDTO {
    @NotEmpty(message = "NAMES_CANNOT_BE_NULL")
    @Size(min = 2, message = "NAMES_INVALID_LENGTH")
    @Size(max = 50, message = "NAMES_INVALID_LENGTH")
    private String names;
    @NotEmpty(message = "LASTNAMES_CANNOT_BE_NULL")
    @Size(min = 2, message = "LASTNAMES_INVALID_LENGTH")
    @Size(max = 50, message = "LASTNAMES_INVALID_LENGTH")
    private String lastnames;
    @NotEmpty(message = "EMAIL_CANNOT_BE_NULL")
    @Size(min = 2, message = "EMAIL_INVALID_LENGTH")
    @Size(max = 50, message = "EMAIL_INVALID_LENGTH")
    @Pattern(regexp = ".+@.+\\..+", message = "EMAIL_INVALID_FORMAT")
    private String email;
    @NotEmpty(message = "CEL_NUMBER_CANNOT_BE_NULL")
    @Size(min = 6, message = "CEL_NUMBER_INVALID_LENGTH")
    @Size(max = 10, message = "CEL_NUMBER_INVALID_LENGTH")
    private String celNumber;
    @NotEmpty(message = "CI_CANNOT_BE_NULL")
    @Size(min = 5, message = "CI_INVALID_LENGTH")
    @Size(max = 50, message = "CI_INVALID_LENGTH")
    private String ci;
    @NotNull(message = "BIRTH_DATE_CANNOT_BE_NULL")
    private Date birthDate;
    private String codSIS;
}
