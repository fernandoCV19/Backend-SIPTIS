package backend.siptis.model.pjo.dto.userDataDTO;


import backend.siptis.auth.entity.SiptisUser;
import backend.siptis.model.entity.userData.UserInformation;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class UserInformationDTO {
    private String names;
    private String lastnames;
    private String email;
    private String celNumber;
    private String ci;
    private Date birthDate;
    private String birthDateString;
    private String codSIS;

    public UserInformationDTO(SiptisUser user) {
        UserInformation information = user.getUserInformation();
        this.setEmail(user.getEmail());
        if (information != null) {
            this.setNames(information.getNames());
            this.setLastnames(information.getLastNames());
            this.setCi(information.getCi());
            this.setCodSIS(information.getCodSIS());
            this.setCelNumber(information.getCelNumber());
            this.setBirthDate(information.getBirthDate());
            this.setBirthDateString(information.getBirthDate().toString());
        }
    }
}
