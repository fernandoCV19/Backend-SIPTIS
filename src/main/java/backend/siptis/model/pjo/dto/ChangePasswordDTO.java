package backend.siptis.model.pjo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

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
