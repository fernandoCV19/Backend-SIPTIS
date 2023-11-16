package backend.siptis.model.pjo.dto.userDataDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ChangePasswordDTO {
    private String emailFrom;
    private String emailTo;
    private String subject;
    private String tokenPassword;
}
