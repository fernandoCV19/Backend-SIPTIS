package backend.siptis.model.pjo.dto.projectManagement;

import backend.siptis.model.entity.projectManagement.Modality;
import backend.siptis.model.entity.projectManagement.Project;
import backend.siptis.model.entity.projectManagement.State;
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

    public ProjectInformationDTO(Project project) {
        this.projectName = project.getName();
        this.period = project.getPeriod();
        Modality projectModality = project.getModality();
        if (projectModality != null) {
            this.modality = projectModality.getName();
        }
        State projectState = project.getState();
        if (projectState != null) {
            this.state = projectState.getName();
        }
        this.phase = project.getPhase();
        this.students = project.getProjectStudents();
        this.tutors = project.getProjectTutors();
        this.teachers = project.getProjectTeachers();
        this.tribunals = project.getProjectTribunals();
        this.supervisors = project.getProjectSupervisors();
        this.areas = project.getAreasNames();
        this.subareas = project.getSubAreasNames();
    }
}
