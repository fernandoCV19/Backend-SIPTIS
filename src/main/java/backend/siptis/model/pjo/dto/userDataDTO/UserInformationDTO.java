package backend.siptis.model.pjo.dto.userDataDTO;


import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class UserInformationDTO {
    private String names;
    private String lastnames;
    private String email;
    private String celNumber;
    private String ci;
    private Date birthDate;
    private String codSIS;
}
