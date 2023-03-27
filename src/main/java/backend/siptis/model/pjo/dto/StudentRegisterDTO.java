package backend.siptis.model.pjo.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class StudentRegisterDTO {
    private String email;
    private String password;
    private String names;
    private String lastnames;
    private String celNumber;
    private String ci;
    private Date birthDate;
    private String codSIS;
    private String career;
}
