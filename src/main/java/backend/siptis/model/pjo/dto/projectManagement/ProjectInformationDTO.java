package backend.siptis.model.pjo.dto.projectManagement;

import backend.siptis.model.pjo.dto.userDataDTO.UserListDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProjectInformationDTO {
    private String projectName;
    private String modality;
    private String period;
    private String phase;
    private String state;
    private String defensePoints;
    private List<UserListDTO> students;
    private List<UserListDTO> tutors;
    private List<UserListDTO> teachers;
    private List<UserListDTO> tribunals;
    private List<UserListDTO> supervisors;
    private List<String> areas;
    private List<String> subareas;
}
