package backend.siptis.model.pjo.dto.user_data;

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
