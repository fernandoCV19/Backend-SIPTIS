package backend.siptis.model.pjo.dto.gestionProyecto;

import backend.siptis.model.entity.gestionProyecto.ProyectoGrado;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectToHomePageDTO {
    private Long projectID;
    private String projectName;
    private List<String> tutors;
    private List<String> teachers;
    private List<String> students;
    private List<String> supervisors;
    private String moreInfo;
    private List<String> areas;
    private List<String> subAreas;
    private Boolean accepted;
    private Boolean reviewed;


    public ProjectToHomePageDTO(ProyectoGrado proyectoGrado, Boolean accepted, Boolean reviewed) {
        projectID = proyectoGrado.getId();
        projectName = proyectoGrado.getNombre();
        tutors = proyectoGrado.getTutores()
                .stream()
                .map(aux -> aux.getTutor().getNombres())
                .toList();
        teachers = proyectoGrado.getDocentes()
                .stream()
                .map(aux -> aux.getDocente().getNombres())
                .toList();
        students = proyectoGrado.getEstudiantes()
                .stream()
                .map(aux -> aux.getEstudiante().getNombres())
                .toList();
        supervisors = proyectoGrado.getSupervisores()
                .stream()
                .map(aux -> aux.getSupervisor().getNombres())
                .toList();
        moreInfo = "";
        areas = proyectoGrado.getAreas()
                .stream()
                .map(aux -> aux.getNombre())
                .toList();
        subAreas = proyectoGrado.getSubAreas()
                .stream()
                .map(aux -> aux.getNombre())
                .toList();
    }
}
