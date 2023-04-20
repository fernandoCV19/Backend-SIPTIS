package backend.siptis.model.pjo.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class StudentEditPersonalInfoDTO {

    private String email;
    private String celNumber;
    private String ci;
    private Date birthDate;


}
