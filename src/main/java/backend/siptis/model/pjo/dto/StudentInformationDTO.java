package backend.siptis.model.pjo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentInformationDTO {

    private String email;
    private String names;
    private String lastnames;
    private String celNumber;
    private String ci;
    private String birthDate;
    private String codSIS;
    private Long careerId;
    private String career;

    public void setBirthDate(Date date){
        DateFormat dtFormat = new SimpleDateFormat("yyyy-MM-dd");
        birthDate = dtFormat.format(date);
    }
}
