package backend.siptis.model.pjo.dto.projectManagement;

import lombok.Data;

import java.util.Date;

@Data
public class DefenseDTO {
    private Long idProject;
    private Long idPlace;
    private Date date;
}
