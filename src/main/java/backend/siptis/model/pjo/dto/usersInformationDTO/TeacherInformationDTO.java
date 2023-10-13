package backend.siptis.model.pjo.dto.usersInformationDTO;

import backend.siptis.model.entity.userData.UserArea;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

public class TeacherInformationDTO {
    private String email;
    private String names;
    private String lastnames;
    private String celNumber;
    private String ci;
    private String birthDate;
    private String codSIS;
    private Long careerId;
    private Set<UserArea> areas;

    public void setBirthDate(Date date) {
        DateFormat dtFormat = new SimpleDateFormat("yyyy-MM-dd");
        birthDate = dtFormat.format(date);
    }

}
